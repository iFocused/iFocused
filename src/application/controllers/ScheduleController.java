package application.controllers;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.AllDayView;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.VirtualGrid;

import application.ui.CalendarFx.CustomPopOverContentNode;
import application.ui.CalendarFx.EntryWithBlockList;
import application.usecases.SessionRepository;
import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class ScheduleController implements Initializable {
	private int calendarStyleCount;
//	private Map<Calendar, ArrayList<EntryWithBlockList>> entryMap;
//	private Map<Calendar, ArrayList<Entry>> entryMap;
	private CalendarView calendarView;
	private CalendarSource myCalendarSource;
	private UseCasePool useCasePool;
	private SessionRepository sessionRepository;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private BorderPane mainPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		calendarStyleCount = 1;

		calendarView = new CalendarView();
		myCalendarSource = new CalendarSource("My Calendars");

		calendarView.getCalendarSources().addAll(myCalendarSource);

		calendarView.setRequestedTime(LocalTime.now());

		Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
			@Override
			public void run() {
				while (true) {
					Platform.runLater(() -> {
						calendarView.setToday(LocalDate.now());
						calendarView.setTime(LocalTime.now());
					});

					try {
						// update every 10 seconds
						sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};
		};

		updateTimeThread.setPriority(Thread.MIN_PRIORITY);
		updateTimeThread.setDaemon(true);
		updateTimeThread.start();

		mainPane.setCenter(calendarView);

//		System.out.println(calendarView.getCalendars().get(0).getName());

		// order of listeners is important
		addCalendarCreationEvent(myCalendarSource);

		calendarView.setEntryDetailsPopOverContentCallback(param -> new CustomPopOverContentNode(param.getPopOver(),
				param.getDateControl(), param.getEntry(), useCasePool.getBlockListRepository().getBlockListsAsList()));

		calendarView.setEntryFactory(param -> {
			DateControl control = param.getDateControl();
			VirtualGrid grid = control.getVirtualGrid();
			ZonedDateTime time = param.getZonedDateTime();
			DayOfWeek firstDayOfWeek = DayOfWeek.of(java.util.Calendar.getInstance().getFirstDayOfWeek());

			ZonedDateTime lowerTime = grid.adjustTime(time, false, firstDayOfWeek);
			ZonedDateTime upperTime = grid.adjustTime(time, true, firstDayOfWeek);

			if (Duration.between(time, lowerTime).abs().minus(Duration.between(time, upperTime).abs()).isNegative()) {
				time = lowerTime;
			} else {
				time = upperTime;
			}

			EntryWithBlockList<?> entry = new EntryWithBlockList<>("New Entry");
			entry.changeStartDate(time.toLocalDate());
			entry.changeStartTime(time.toLocalTime());
			entry.changeEndDate(entry.getStartDate());
			entry.changeEndTime(entry.getStartTime().plusHours(1));

			if (control instanceof AllDayView) {
				entry.setFullDay(true);
			}

			return entry;
		});

		importCalendars();
		addEntryListener();

	}

	public ScheduleController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.sessionRepository = useCasePool.getSessionRepository();
		this.fxmlViewBuilder = fxmlViewBuilder;
//		entryMap = new HashMap<>();
	}

	private void importCalendars() {

		if (this.sessionRepository.getCalendarsMap().size() > 0) {

			// load calendars and its entries
			for (String calendarName : this.sessionRepository.getCalendarsMap().keySet()) {
				Calendar tmpCalendar;
				if (calendarName.equals("Default")) {
					tmpCalendar = calendarView.getCalendars().get(0);
				} else {
					tmpCalendar = new Calendar(calendarName);
					tmpCalendar.setStyle(getCurrStyle());
					myCalendarSource.getCalendars().add(tmpCalendar);
				}

				parseCalendarEntries(tmpCalendar, this.sessionRepository.getCalendarsMap().get(calendarName));

			}
		}
	}

	private void parseCalendarEntries(Calendar targetCalendar, List<EntryWithBlockList<?>> entryList) {
		for (EntryWithBlockList<?> entry : entryList) {
			final LocalDate startDate = LocalDate.parse(entry.getStartDateRep());
			final LocalDate endDate = LocalDate.parse(entry.getEndDateRep());
			final LocalTime startTime = LocalTime.parse(entry.getStartTimeRep());
			final LocalTime endTime = LocalTime.parse(entry.getEndTimeRep());
			entry.changeStartDate(startDate);
			entry.changeEndDate(endDate);
			entry.changeStartTime(startTime);
			entry.changeEndTime(endTime);
			targetCalendar.addEntry(entry);
		}
	}

	private void addCalendarCreationEvent(CalendarSource myCalendarSource) {
		calendarView.getCalendarSources().addListener(new ListChangeListener<CalendarSource>() {

			@Override
			public void onChanged(Change<? extends CalendarSource> calendarSourceLst) {
				TextInputDialog td = new TextInputDialog("Enter here");
				td.initOwner(fxmlViewBuilder.getMainStage());
				td.setTitle("Choose Calendar Name");
				td.setHeaderText("Enter calendar name");

				Optional<String> result = td.showAndWait();
				if (result.isPresent()) {
					if (result.get().isEmpty() || result.get().equals("Enter here")) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.getDialogPane().setStyle("-fx-font-family: 'calibri';");
						alert.initOwner(fxmlViewBuilder.getMainStage());
						alert.setTitle("Invalid Calendar Name");
						alert.setHeaderText("Please enter a valid calendar name");
						alert.setContentText("Invalid calendar name was entered");

						alert.showAndWait();
						// fire event change to prompt the user to re-enter the calendar name
						calendarView.getCalendarSources().retainAll(calendarView.getCalendarSources());
						return;
					}

					CalendarSource tmpCalendarSource = calendarSourceLst.getList()
							.get(calendarSourceLst.getList().size() - 1);

					Calendar newCalendar = tmpCalendarSource.getCalendars().get(calendarSourceLst.getList()
							.get(calendarSourceLst.getList().size() - 1).getCalendars().size() - 1);

					calendarSourceLst.getList().remove(tmpCalendarSource);

					newCalendar.setStyle(getCurrStyle());
					newCalendar.setName(result.get());
					myCalendarSource.getCalendars().add(newCalendar);
					newCalendar.addEventHandler(getEntryListenerHandler());

				} else {
					calendarSourceLst.getList().remove(calendarSourceLst.getList().size() - 1);
				}

			}
		});
	}

	private EventHandler<CalendarEvent> getEntryListenerHandler() {
		EventHandler<CalendarEvent> eventHandler = new EventHandler<CalendarEvent>() {

			@Override
			public void handle(CalendarEvent event) {
				// ensuring no delete request was sent
				if (event.getCalendar() != null) {
					Calendar calendar = event.getCalendar();
					Map<LocalDate, List<Entry<?>>> tmpCalendarMap = (calendar.findEntries(LocalDate.now(),
							LocalDate.now().plusYears(25),
							useCasePool.getUserManager().getUser().getTimeZone().toZoneId()));

						tmpCalendarMap.values().forEach(entryLst -> {

							entryLst.forEach(entry -> {
								// since we used entry factory where we create
								// an entry of type EntryWithBlockList, then
								// we can cast it back to an EntryWithBlockList since
								// we know it should have all the required fields
								EntryWithBlockList<?> entryWithBlockList = (EntryWithBlockList<?>) entry;
								sessionRepository.removeEntryByReference(entryWithBlockList);
								sessionRepository.createCalendarEntry(calendar, entryWithBlockList);
							});
						});
						sessionRepository.exportCalendarsData();
				} else {
					sessionRepository.removeEntryByReference((EntryWithBlockList<?>) event.getEntry());
					sessionRepository.exportCalendarsData();
				}

			}
		};

		return eventHandler;
	}

	private void addEntryListener() {
		for (Calendar calendar : calendarView.getCalendars()) {
			calendar.addEventHandler(getEntryListenerHandler());
		}

	}

	private Style getCurrStyle() {
		Style selectedStyle = null;

		switch (this.calendarStyleCount % 6) {
		case 0:
			selectedStyle = Style.STYLE1;
			break;

		case 1:
			selectedStyle = Style.STYLE2;
			break;

		case 2:
			selectedStyle = Style.STYLE3;
			break;

		case 3:
			selectedStyle = Style.STYLE4;
			break;

		case 4:
			selectedStyle = Style.STYLE5;
			break;

		case 5:
			selectedStyle = Style.STYLE6;
			break;

		}

		this.calendarStyleCount++;
		return selectedStyle;
	}

}
