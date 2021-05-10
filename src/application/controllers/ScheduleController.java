package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.calendarfx.view.CalendarView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ScheduleController implements Initializable {
	
    @FXML
    private BorderPane mainPane;
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("here");
		CalendarView calendar = new CalendarView();
		mainPane.setCenter(calendar);
	}

}
