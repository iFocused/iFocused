package application.usecases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import application.entities.Session;

public class SessionRepository {

	private ArrayList<Session> sessions;

	/**
	 * Constructor for the session repository
	 */
	public SessionRepository() {
		this.sessions = new ArrayList<>();
	}

	/**
	 * Adds a new session.
	 * 
	 * @param session The session to be added
	 */
	public void createSession(int sessionId, int blockListId) {
		this.sessions.add(new Session(sessionId, blockListId));
	}

	/**
	 * Removes a session.
	 * 
	 * @param session The session to be removed
	 */
	public void removeSessionById(int sessionId) {
		for(Session session : this.sessions) {
			if(session.getSessionId() == sessionId) {
				this.sessions.remove(session);
				return;
			}
		}
	}

	/**
	 * Return a list of all the sessions in this repository
	 * 
	 * @return a list of all the sessions
	 */
	public ArrayList<Session> getSessions() {
		return this.sessions;
	}

	/**
	 * Return a list of session prior to the given <time> in ascending order.
	 * 
	 * @param time 		The time to be counted up to
	 * @return a list of sessions up to a certain time in ascending order
	 */
	public ArrayList<Session> getNSessionsAsc(String time) {
		ArrayList<Session> filteredSessions = new ArrayList<>();
		Date dateTime;

		try {

			dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(time);
			for (Session session : this.sessions) {
				Date tmpDateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(session.getStartTime());
				if (tmpDateTime.before(dateTime)) {
					filteredSessions.add(session);
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return filteredSessions;
	}

	/**
	 * Return a list of session prior to the given <time> in descending order.
	 * 
	 * @param time The time to be counted up to
	 * @return a list of sessions up to a certain time in descending order
	 */
	public ArrayList<Session> getNSessionsDesc(String time) {
		ArrayList<Session> sessions = this.getNSessionsAsc(time);
		Collections.reverse(sessions);
		return sessions;
	}
}
