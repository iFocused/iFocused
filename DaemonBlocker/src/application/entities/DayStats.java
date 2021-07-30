package application.entities;

import java.time.LocalDateTime;

public class DayStats {
	private int timeComputer;
	private int timeStudying;
	private int pomodoroSessions;
	private int pointsEarned;
	private LocalDateTime todaysDate;

	public DayStats(int timeComputer, int timeStudying, int pomodoroSessions, int pointsEarned) {
		this.timeComputer = timeComputer;
		this.timeStudying = timeStudying;
		this.pomodoroSessions = pomodoroSessions;
		this.pointsEarned = pointsEarned;
		this.todaysDate = LocalDateTime.now();
	}

	public int getTimeComputer() {
		return this.timeComputer;
	}

	public int getTimeStudying() {
		return this.timeStudying;
	}

	public int getPomodoroSessions() {
		return this.pomodoroSessions;
	}

	public int getPointsEarned() {
		return this.pointsEarned;
	}

	public LocalDateTime getTodaysDate() {
		return this.todaysDate;
	}

	public void setTimeComputer(int newTimeComputer) {
		this.timeComputer = newTimeComputer;
	}

	public void setTimeStudying(int newTimeStudying) {
		this.timeStudying = newTimeStudying;
	}

	public void setPomodoroSessions(int newPomodoroSessions) {
		this.pomodoroSessions = newPomodoroSessions;
	}

	public void setPointsEarned(int newPointsEarned) {
		this.pointsEarned = newPointsEarned;
	}

}
