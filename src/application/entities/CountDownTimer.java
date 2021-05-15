package application.entities;

import javafx.animation.Animation.Status;
import application.ui.CircleIndicator.RingProgressIndicator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class CountDownTimer {
	private int timeMinutes, timeSeconds;
	private int countDownTime;
	RingProgressIndicator ringProgressIndicator;
	Timeline timeline;

	public CountDownTimer(int countDownTime) {
		this.countDownTime = countDownTime;
		this.timeMinutes = countDownTime;
		ringProgressIndicator = new RingProgressIndicator();
	}

	private String formatTimeString() {
		if (this.timeSeconds < 10)
			return String.valueOf(this.timeMinutes) + ":0" + String.valueOf(this.timeSeconds);
		if (this.timeSeconds == 60)
			return String.valueOf(this.timeMinutes) + ":00";
		return String.valueOf(this.timeMinutes) + ":" + String.valueOf(this.timeSeconds);
	}

	public void startCountDown() {
		// counting down from the specified Pomodoro session and updating the
		// UI label respectively
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			// handling event that will occur every second
			public void handle(ActionEvent event) {
				if (timeSeconds == 0) {
					timeSeconds = 60;
					timeMinutes--;
					timeSeconds--;
					ringProgressIndicator.setProgress(ringProgressIndicator.getProgress() + (100 / countDownTime),
							formatTimeString());
				} else {
					timeSeconds--;
					ringProgressIndicator.setProgress(ringProgressIndicator.getProgress(), formatTimeString());
				}

				// updating ring time
				if (timeMinutes <= 0) {
					timeline.stop();
				}
			}
		}));

		timeline.playFromStart();
	}
	
	public Status getStatus() {
		return this.timeline.getStatus();
	}

	public Timeline getTimeline() {
		return this.timeline;
	}
	
	public RingProgressIndicator getProgressIndicator() {
		return ringProgressIndicator;
	}

	public void setTimeMins(int newTimeMins) {
		this.timeMinutes = newTimeMins;
	}

	public void setTimeSecs(int newTimeSecs) {
		this.timeSeconds = newTimeSecs;
	}

}
