package application.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayStats {
	private int timeComputer;
	private int timeStudying;
	private int pomodoroSessions;
	private int pointsEarned;
	private String todaysDate;

	public DayStats(int timeComputer, int timeStudying, int pomodoroSessions, int pointsEarned) {
		this.timeComputer = timeComputer;
		this.timeStudying = timeStudying;
		this.pomodoroSessions = pomodoroSessions;
		this.pointsEarned = pointsEarned;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		this.todaysDate = formatter.format(new Date());
	}

	public int getTimeComputer() {
		return timeComputer;
	}

	public int getTimeStudying() {
		return timeStudying;
	}

	public int getPomodoroSessions() {
		return pomodoroSessions;
	}

	public int getPointsEarned() {
		return pointsEarned;
	}

	public String getTodaysDate() {
		return todaysDate;
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
