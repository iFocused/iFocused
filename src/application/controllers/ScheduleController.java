package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.calendarfx.view.CalendarView;

import application.usecases.UseCasePool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ScheduleController implements Initializable {
	private UseCasePool useCasePool;
	
    @FXML
    private BorderPane mainPane;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		CalendarView calendar = new CalendarView();
		mainPane.setCenter(calendar);
	}
	
	public ScheduleController(UseCasePool useCasePool) {
		this.useCasePool = useCasePool;
	}

}
