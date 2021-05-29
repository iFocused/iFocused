package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;

import application.ui.CircleIndicator.RingProgressIndicator;
import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.animation.Animation.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TimerController implements Initializable {

	final static int SESSION = 15, BREAK = 5, ROUNDS = 4;
	private RingProgressIndicator ringProgressIndicator;
	private CountDownTimer countDownTimer;
	private UseCasePool useCasePool;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private Text roundsLbl;

	@FXML
	private Text breakLbl;

	@FXML
	private Text sessionLbl;

	@FXML
	private Button startBtn;

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
	private Text descriptionLbl;

	@FXML
	private Text roundLbl;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		applySliderListeners();
		descriptionLbl.setVisible(false);
		roundLbl.setVisible(false);

		/* Creating circle component */
		countDownTimer = new CountDownTimer((int) sessionSlider.getValue());
		ringProgressIndicator = countDownTimer.getProgressIndicator();
		ringProgressIndicator.setProgress(0, sessionLbl.getText());
		timerPane.setCenter(ringProgressIndicator);
	}

	@FXML
	void onHandleCreate(ActionEvent event) {

	}

	@FXML
	void onHandleReset(ActionEvent event) {
		resetTimer();

	}

	@FXML
	void onHandleResetDefaults(ActionEvent event) {
		sessionSlider.setValue(SESSION);
		roundsSlider.setValue(ROUNDS);
		breakSlider.setValue(BREAK);
	}

	@FXML
	void onHandleStart(ActionEvent event) {
		descriptionLbl.setVisible(true);
		roundLbl.setVisible(true);
		settingsPane.setDisable(true);
		startBtn.setDisable(true);
		// run <rounds> many times
		for (int i = 0; i < (int) roundsSlider.getValue(); i++) {
			if (countDownTimer.getTimeline() == null)
				countDownTimer.startCountDown();
			else if (countDownTimer.getStatus() != Status.RUNNING)
				countDownTimer.startCountDown();

		}

	}

	public TimerController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

	private void resetTimer() {
		if (countDownTimer.getTimeline() != null) {
			countDownTimer.getTimeline().stop();
			descriptionLbl.setVisible(false);
			roundLbl.setVisible(false);
			startBtn.setDisable(false);
			settingsPane.setDisable(false);
			ringProgressIndicator.setProgress(0, sessionLbl.getText());
			this.countDownTimer.setTimeMins((int) sessionSlider.getValue());
			this.countDownTimer.setTimeSecs(0);
		}
	}

	private void applySliderListeners() {
		sessionSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				sessionLbl.setText(String.valueOf(newValue.intValue()) + ":00");
				countDownTimer.setTimeMins(newValue.intValue());
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

}