package application.entities;

import java.util.TimeZone;

public class User {
	private int points;
	private int pomodoroSessionsCount;
	private int tasksCompleted;
	private String username;
	private BlockMode blockMode;
	private TimeZone timeZone;

	public User() {
		this("", null);
	}

	public User(String username, TimeZone timeZone) {
		this.points = 0;
		this.blockMode = BlockMode.SIMPLE;
		this.username = username;
		this.timeZone = timeZone;
	}

	public void addPoint() {
		this.points++;
	}

	public int getPoints() {
		return this.points;
	}

	public int getPomodoroSessionsCount() {
		return pomodoroSessionsCount;
	}

	public void setPomodoroSessionsCount(int pomodoroSessionsCount) {
		this.pomodoroSessionsCount = pomodoroSessionsCount;
	}

	public int getTasksCompleted() {
		return tasksCompleted;
	}

	public void setTasksCompleted(int tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}

	public String getUsername() {
		return this.username;
	}

	public BlockMode getBlockMode() {
		return this.blockMode;
	}

	public TimeZone getTimeZone() {
		return this.timeZone;
	}

	public void setPoints(int newPoints) {
		this.points = newPoints;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public void setBlockMode(BlockMode newBlockMode) {
		this.blockMode = newBlockMode;
	}

	public void setTimeZone(TimeZone newTimeZone) {
		this.timeZone = newTimeZone;
	}

}