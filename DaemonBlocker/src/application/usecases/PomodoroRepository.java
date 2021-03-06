package application.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import application.entities.PomodoroSession;
import application.gateways.PomodoroRepositoryGateway;

public class PomodoroRepository {
	private ArrayList<PomodoroSession> pomodoroSessions;
	private final PomodoroRepositoryGateway pomodoroRepositoryGateway;

	/**
	 * Constructor for the Pomodoro Session repository
	 */
	public PomodoroRepository(PomodoroRepositoryGateway pomodoroRepositoryGateway) {
		this.pomodoroSessions = new ArrayList<>();
		this.pomodoroRepositoryGateway = pomodoroRepositoryGateway;
		this.pomodoroRepositoryGateway.populateUserData(this);
	}

	/**
	 * Adds a new Pomodoro Session.
	 * 
	 * @param PomodoroSession The PomodoroSession to be added
	 */
	public void createPomodoroSession(int breakDuration) {
		this.pomodoroSessions.add(new PomodoroSession(breakDuration));
	}

	/**
	 * Removes a Pomodoro Session.
	 * 
	 * @param PomodoroSession The PomodoroSession to be removed
	 */
	public void removePomodoroSession(PomodoroSession pomodoroSession) {
		this.pomodoroSessions.remove(pomodoroSession);
	}

	/**
	 * Return a list of all the Pomodoro Sessions in this repository
	 * 
	 * @return a list of all the Pomodoro Sessions
	 */
	public ArrayList<PomodoroSession> getPomodoroSessions() {
		return this.pomodoroSessions;
	}

	/**
	 * Return a list of Pomodoro Sessions prior to the given <time> in ascending
	 * order.
	 * 
	 * @param time The time to be counted up to
	 * @return a list of Pomodoro Sessions up to a certain time in ascending order
	 */
	public ArrayList<PomodoroSession> getNPomodoroSessionsAsc(LocalDateTime givenTime) {
		ArrayList<PomodoroSession> filteredpomodoroSessions = new ArrayList<>();

		for (PomodoroSession pomodoroSession : this.pomodoroSessions) {
			LocalDateTime tmpDateTime = pomodoroSession.getStartTime();
			if (tmpDateTime.isBefore(givenTime)) {
				filteredpomodoroSessions.add(pomodoroSession);
			}
		}

		return filteredpomodoroSessions;
	}

	/**
	 * Return a list of Pomodoro Sessions prior to the given <time> in descending
	 * order.
	 * 
	 * @param time The time to be counted up to
	 * @return a list of pomodoroSessions up to a certain time in descending order
	 */
	public ArrayList<PomodoroSession> getNPomodoroSessionsDesc(LocalDateTime time) {
		ArrayList<PomodoroSession> pomodoros = this.getNPomodoroSessionsAsc(time);
		Collections.reverse(pomodoros);
		return pomodoros;
	}

	/**
	 * Return the most recent Pomodoro Session
	 * 
	 * @return the most recent Pomodoro Session
	 */
	public PomodoroSession getMostRecentPomodoroSession() {
		ArrayList<PomodoroSession> sessionsAsc = this.getPomodoroSessions();
		return sessionsAsc.get(sessionsAsc.size() - 1);
	}
}
