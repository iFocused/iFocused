package application.controllers;

import javafx.animation.Animation.Status;
import application.ui.CircleIndicator.RingProgressIndicator;
import application.usecases.UseCasePool;
import application.usecases.UserManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;

class CountDownTimer {
	private final UserManager userManager;
	private int timeMinutes, timeSeconds;
	private int countDownTime;
	private int countDownBreakTime;
	RingProgressIndicator ringProgressIndicator;
	Timeline timeline;

	public CountDownTimer(int countDownTime, int countDownBreakTime, UseCasePool useCasePool) {
		this.timeMinutes = this.countDownTime = countDownTime;
		this.countDownBreakTime = countDownBreakTime;
		this.userManager = useCasePool.getUserManager();
		ringProgressIndicator = new RingProgressIndicator();
	}

	private String formatTimeString() {
		if (this.timeSeconds < 10)
			return String.valueOf(this.timeMinutes) + ":0" + String.valueOf(this.timeSeconds);
		if (this.timeSeconds == 60)
			return String.valueOf(this.timeMinutes) + ":00";
		return String.valueOf(this.timeMinutes) + ":" + String.valueOf(this.timeSeconds);
	}

	void startCountDown(int rounds, int currRound, boolean isOnBreak, Text roundLbl, Text sessionLbl,
			Text descriptionLbl) {
		// counting down from the specified Pomodoro session and updating the
		// UI label respectively
		if (!isOnBreak) {
			descriptionLbl.setText("In Session");
			roundLbl.setVisible(true);
			roundLbl.setText("Round: " + (currRound + 1));
		} else {
			roundLbl.setVisible(false);
			descriptionLbl.setText("In Break");
		}

		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			// handling event that will occur every second
			public void handle(ActionEvent event) {
				// updating ring time (accounting for the first execution of the timer where we
				// subtract right away)
				if (timeMinutes <= 0 && timeSeconds <= 0) {
					ringProgressIndicator.setProgress(100, formatTimeString());
					System.out.println("stopping timer....");
					timeline.stop();
					if (currRound != rounds - 1) {
						timeSeconds = 0;
						if (isOnBreak) {
							timeMinutes = countDownTime;
							ringProgressIndicator.setProgress(timeMinutes, sessionLbl.getText());
							startCountDown(rounds, currRound, !isOnBreak, roundLbl, sessionLbl, descriptionLbl);
						} else {
							timeMinutes = countDownBreakTime;
							ringProgressIndicator.setProgress(timeMinutes, sessionLbl.getText());
							startCountDown(rounds, currRound + 1, !isOnBreak, roundLbl, sessionLbl, descriptionLbl);

						}
					} else {
						// if it got to here, then the pomodoro session is done
						// incrementing statistics in dashboard
						userManager.incrementPomodoroCounter();
						userManager.SetIsUserManagerChangedProperty(true);
						ringProgressIndicator.setProgress(0, sessionLbl.getText());
					}
				} else {

					if (timeSeconds == 0 && timeMinutes > 0) {
						timeSeconds = 60;
						timeMinutes--;
					}

					timeSeconds--;
					if(!isOnBreak) {
						ringProgressIndicator.setProgress(
								ringProgressIndicator.getProgress() + ((100.0 / countDownTime) / 60.0), formatTimeString());	
					} else {
						ringProgressIndicator.setProgress(
								ringProgressIndicator.getProgress() + ((100.0 / countDownBreakTime) / 60.0), formatTimeString());
					}
					
				}
			}
		}));

		timeline.playFromStart();
	}

	Status getStatus() {
		return this.timeline.getStatus();
	}

	Timeline getTimeline() {
		return this.timeline;
	}

	RingProgressIndicator getProgressIndicator() {
		return ringProgressIndicator;
	}

	void setTimeMins(int newTimeMins) {
		this.timeMinutes = this.countDownTime = newTimeMins;
	}
	
	void setBreakTime(int newBreakTime) {
		this.countDownBreakTime = newBreakTime;
	}

	void setTimeSecs(int newTimeSecs) {
		this.timeSeconds = newTimeSecs;
	}

}
