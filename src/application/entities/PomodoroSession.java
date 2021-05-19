package application.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PomodoroSession {
	private Session session;
	private int breakDuration;
	private String startTime;
	private int timeUntilStateChange;
	private String endTime;

	public PomodoroSession(Session session, int breakDuration) {
		this.session = session;
		this.breakDuration = breakDuration;
		this.timeUntilStateChange = 0;

		// start counting time
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.startTime = formatter.format(new Date());
		this.endTime = "";
	}

	public void changeStatus(SessionStatus newSessionStatus) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.endTime = formatter.format(new Date());
		// this.timeUntilStateChange = this.endTime - this.startTime (in minutes)

		// resetting for next round
		this.startTime = formatter.format(new Date());

		// applying actual session status change
		this.session.setSessionStatus(newSessionStatus);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getBreakDuration() {
		return breakDuration;
	}

	public void setBreakDuration(int breakDuration) {
		this.breakDuration = breakDuration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getTimeUntilStateChange() {
		return timeUntilStateChange;
	}

	public void setTimeUntilStateChange(int timeUntilStateChange) {
		this.timeUntilStateChange = timeUntilStateChange;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
