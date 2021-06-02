package application.controllers;

import javafx.animation.Animation.Status;
import application.ui.CircleIndicator.RingProgressIndicator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

class CountDownTimer {
	private int timeMinutes, timeSeconds;
	private int countDownTime;
	RingProgressIndicator ringProgressIndicator;
	Timeline timeline;

	public CountDownTimer(int countDownTime) {
		this.timeMinutes = this.countDownTime = countDownTime;
		ringProgressIndicator = new RingProgressIndicator();
	}

	private String formatTimeString() {
		if (this.timeSeconds < 10)
			return String.valueOf(this.timeMinutes) + ":0" + String.valueOf(this.timeSeconds);
		if (this.timeSeconds == 60)
			return String.valueOf(this.timeMinutes) + ":00";
		return String.valueOf(this.timeMinutes) + ":" + String.valueOf(this.timeSeconds);
	}

	void startCountDown() {
		// counting down from the specified Pomodoro session and updating the
		// UI label respectively
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
				} else {

					if (timeSeconds == 0 && timeMinutes > 0) {
						timeSeconds = 60;
						timeMinutes--;
					}

					timeSeconds--;
					ringProgressIndicator.setProgress(
							ringProgressIndicator.getProgress() + ((100.0 / countDownTime) / 60.0), formatTimeString());
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

	void setTimeSecs(int newTimeSecs) {
		this.timeSeconds = newTimeSecs;
	}

}
