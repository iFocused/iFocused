package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.DateControl;

import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class ScheduleController implements Initializable {
	private int calendarStyleCount;
	private CalendarView calendar;
	private UseCasePool useCasePool;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private BorderPane mainPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		calendarStyleCount = 1;
//		calendar = new CalendarView();
//		Calendar calendarOrig = new Calendar("Demo");
//		mainPane.setCenter(calendar);

//		calendar.addEventHandler(CalendarEvent.ENTRY_TITLE_CHANGED, new EventHandler<CalendarEvent>() {
//
//			@Override
//			public void handle(CalendarEvent arg0) {
//				System.out.println("smth changed");
//
//			}
//		});

		CalendarView calendarView = new CalendarView();

		// look into events and callbacks
//		calendarView.setDefaultCalendarProvider(new Callback<DateControl, Calendar>() {
//			
//			@Override
//			public Calendar call(DateControl arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("lool");
//				return null;
//			}
//		});

		// creating calendars
//		Calendar studyPlan = new Calendar("Study Plans");
//		Calendar assignments = new Calendar("Assignments");
//
//		studyPlan.setStyle(Style.STYLE1);
//		assignments.setStyle(Style.STYLE2);

		CalendarSource myCalendarSource = new CalendarSource("My Calendars");
//		myCalendarSource.getCalendars().addAll(studyPlan, assignments);

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
			public void handle(CalendarEvent arg0) {
				System.out.println("smth changed");

			}
		};

//		studyPlan.addEventHandler(handler);
		calendarView.getCalendarSources().addListener(new ListChangeListener<CalendarSource>() {

			@Override
			public void onChanged(Change<? extends CalendarSource> calenderSourceLst) {
				TextInputDialog td = new TextInputDialog("Enter here");
				td.setHeaderText("Enter calendar name");

				Optional<String> result = td.showAndWait();
				if (result.isPresent()) {
					// ok was pressed
					CalendarSource tmpCalendarSource = calenderSourceLst.getList()
							.get(calenderSourceLst.getList().size() - 1);
					
					Calendar newCalendar = tmpCalendarSource
							.getCalendars().get(calenderSourceLst.getList().get(calenderSourceLst.getList().size() - 1)
									.getCalendars().size() - 1);

					calenderSourceLst.getList().remove(tmpCalendarSource);

					newCalendar.setStyle(getCurrStyle());
					newCalendar.setName(result.get());
					myCalendarSource.getCalendars().add(newCalendar);
					calendarStyleCount++;

				}

//				System.out.println(calenderSourceLst.getList().get(calenderSourceLst.getList().size() - 1).getName());
//				System.out.println(calenderSourceLst
//						.getList().get(calenderSourceLst.getList().size() - 1).getCalendars().get(calenderSourceLst
//								.getList().get(calenderSourceLst.getList().size() - 1).getCalendars().size() - 1)
//						.getName());

			}
		});

//		calendarView.showAddCalendarButtonProperty().set(false);

//		System.out.println(calendarView.getCalendarSources().get(0).getName());
//		System.out.println(calendarView.getCalendars().get(0).getName());

//		calendarView.addEventHandler(CalendarEvent.CALENDAR_CHANGED, new EventHandler<CalendarEvent>() {
//
//			@Override
//			public void handle(CalendarEvent arg0) {
//				System.out.println("yoooo");
//
//			}
//		});

	}

	public ScheduleController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.fxmlViewBuilder = fxmlViewBuilder;

	}

	private Style getCurrStyle() {
		switch (this.calendarStyleCount % 6) {
		case 0:
			return Style.STYLE1;

		case 1:
			return Style.STYLE2;

		case 2:
			return Style.STYLE3;

		case 3:
			return Style.STYLE4;

		case 4:
			return Style.STYLE5;

		case 5:
			return Style.STYLE6;

		}
		return null;
	}

}
