package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class ScheduleController implements Initializable {
	private int calendarStyleCount;
	private Map<Calendar, ArrayList<Entry>> entryMap;
	private CalendarView calendarView;
	private UseCasePool useCasePool;
	private SessionRepository sessionRepository;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private BorderPane mainPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		calendarStyleCount = 1;

		calendarView = new CalendarView();
		CalendarSource myCalendarSource = new CalendarSource("My Calendars");

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

		EventHandler<CalendarEvent> handler = new EventHandler<CalendarEvent>() {

			@Override
			public void handle(CalendarEvent calEvent) {
				System.out.println("smth changed");

			}
		};

//		studyPlan.addEventHandler(handler);

		// order of listeners is important
		addCalendarCreationEvent(myCalendarSource);
		addEntryListener(myCalendarSource);

	}

	public ScheduleController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.sessionRepository = useCasePool.getSessionRepository();
		this.fxmlViewBuilder = fxmlViewBuilder;
		entryMap = new HashMap<>();
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

				} else {
					calendarSourceLst.getList().remove(calendarSourceLst.getList().size() - 1);
				}

			}
		});
	}

	private void addEntryListener(CalendarSource myCalendarSource) {
		calendarView.getCalendarSources().addListener(new ListChangeListener<CalendarSource>() {

			@Override
			public void onChanged(Change<? extends CalendarSource> calendarSourceLst) {
				System.out.println("trigerred");
				for (Calendar calendar : myCalendarSource.getCalendars()) {
					calendar.addEventHandler(evt -> {
						// check if the calendar entry (session) already exists
						if (entryMap.containsKey(calendar)) {
							entryMap.get(calendar).clear();
						} else {
							entryMap.put(calendar, new ArrayList<>());
						}

						Map<LocalDate, List<Entry<?>>> tmpCalendarMap = (calendar.findEntries(LocalDate.now(),
								LocalDate.now().plusYears(5),
								useCasePool.getUserManager().getUser().getTimeZone().toZoneId()));

						if (tmpCalendarMap.size() > 0) {
							tmpCalendarMap.values().forEach(entryLst -> {

								entryLst.forEach(entry -> {
									entryMap.get(calendar).add(entry);
									System.out.println(entry.getTitle());
								});

//								temporary
//								sessionRepository.createSession(0);

							});
						}

						System.out.println("change was done!");
					});
				}

			}

		});

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
