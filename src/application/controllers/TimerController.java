package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;

import application.ui.CircleIndicator.RingProgressIndicator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.lang.String;

public class TimerController implements Initializable {

	final static int SESSION = 25, BREAK = 5, ROUNDS = 4;
	private RingProgressIndicator ringProgressIndicator;
	private int timeMins, timeSecs;
	private Timeline timeline;

	@FXML
	private Text roundsLbl;

	@FXML
	private Text breakLbl;

	@FXML
	private Text sessionLbl;

	@FXML
	private JFXSlider sessionSlider;

	@FXML
	private JFXSlider roundsSlider;

	@FXML
	private JFXSlider breakSlider;

	@FXML
	private BorderPane timerPane;

	@FXML
	private Pane settingsPane;

	@FXML
	private Button startBtn;

	private void applySliderListeners() {
		sessionSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				sessionLbl.setText(String.valueOf(newValue.intValue()) + ":00");
				ringProgressIndicator.setProgress(0, sessionLbl.getText());
			}
		});

		roundsSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				roundsLbl.setText(String.valueOf(newValue.intValue()));
			}
		});

		breakSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				breakLbl.setText(String.valueOf(newValue.intValue()) + ":00");
			}
		});
	}

	private String formatTimeString() {
		if (this.timeSecs < 10)
			return String.valueOf(this.timeMins) + ":0" + String.valueOf(this.timeSecs);
		if (this.timeSecs == 60)
			return String.valueOf(this.timeMins) + ":00";
		return String.valueOf(this.timeMins) + ":" + String.valueOf(this.timeSecs);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		applySliderListeners();

		/* Creating circle component */
		ringProgressIndicator = new RingProgressIndicator();
		ringProgressIndicator.setProgress(0, sessionLbl.getText());
		timerPane.setCenter(ringProgressIndicator);
	}

	@FXML
	void onHandleCreate(ActionEvent event) {
		ringProgressIndicator.setProgress(ringProgressIndicator.getProgress() + 1, "23:00");
	}

	@FXML
	void onHandleReset(ActionEvent event) {
		timeline.stop();
		startBtn.setDisable(false);
		settingsPane.setDisable(false);
		ringProgressIndicator.setProgress(0, sessionLbl.getText());
		this.timeMins = (int) sessionSlider.getValue();
		this.timeSecs = 0;

	}

	@FXML
	void onHandleResetDefaults(ActionEvent event) {
		sessionSlider.setValue(SESSION);
		roundsSlider.setValue(ROUNDS);
		breakSlider.setValue(BREAK);
	}

	@FXML
	void onHandleStart(ActionEvent event) {
		settingsPane.setDisable(true);
		startBtn.setDisable(true);
		timeMins = (int) sessionSlider.getValue();

		// counting down from the specified Pomodoro session and updating the
		// UI label respectively
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			// handling event that will occur every second
			public void handle(ActionEvent event) {
				if (timeSecs == 0) {
					timeSecs = 60;
					timeMins--;
					timeSecs--;
					ringProgressIndicator.setProgress(
							ringProgressIndicator.getProgress() + (100 / sessionSlider.getValue()), formatTimeString());
				} else {
					timeSecs--;
					ringProgressIndicator.setProgress(ringProgressIndicator.getProgress(), formatTimeString());
				}

				// updating ring time
				if (timeMins <= 0) {
					timeline.stop();
				}
			}
		}));

		timeline.playFromStart();
	}

}
