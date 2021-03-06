package application.entities;

import java.time.LocalDateTime;

public class PomodoroSession {
	private int breakDuration;
	private LocalDateTime startTime;
	private int timeUntilStateChange;
	private LocalDateTime endTime;

	public PomodoroSession(int breakDuration) {
		this.breakDuration = breakDuration;
		this.timeUntilStateChange = 0;

		// start counting time
		this.startTime = LocalDateTime.now();
		this.endTime = null;
	}

	public void changeStatus(SessionStatus newSessionStatus) {
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		this.endTime = formatter.format(new Date());
		this.endTime = LocalDateTime.now();
		// this.timeUntilStateChange = this.endTime - this.startTime (in minutes)

		// resetting for next round
		this.startTime = LocalDateTime.now();
	}

	public int getBreakDuration() {
		return breakDuration;
	}

	public void setBreakDuration(int breakDuration) {
		this.breakDuration = breakDuration;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public int getTimeUntilStateChange() {
		return timeUntilStateChange;
	}

	public void setTimeUntilStateChange(int timeUntilStateChange) {
		this.timeUntilStateChange = timeUntilStateChange;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}
