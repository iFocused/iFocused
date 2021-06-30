package application.usecases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import application.entities.Session;
import application.gateways.SessionRepositoryGateway;

public class SessionRepository {

	private ArrayList<Session> sessions;
	private int sessionIdTracker;
	private final SessionRepositoryGateway sessionRepositoryGateway;

	/**
	 * Constructor for the session repository
	 */
	public SessionRepository(SessionRepositoryGateway sessionRepositoryGateway) {
		this.sessions = new ArrayList<>();
		this.sessionRepositoryGateway = sessionRepositoryGateway;
		this.sessionRepositoryGateway.populateUserData(this);
		this.sessionIdTracker = 0;
	}

	/**
	 * Adds a new session.
	 * 
	 * @param session The session to be added
	 */
	public void createSession(int blockListId) {
		this.sessions.add(new Session(sessionIdTracker, blockListId));
		this.sessionIdTracker++;
	}

	/**
	 * Removes a session.
	 * 
	 * @param session The session to be removed
	 */
	public void removeSessionById(int sessionId) {
		for (Session session : this.sessions) {
			if (session.getSessionId() == sessionId) {
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
	 * @param time The time to be counted up to
	 * @return a list of sessions up to a certain time in ascending order
	 */
	public ArrayList<Session> getNSessionsAsc(LocalDateTime givenTime) {
		ArrayList<Session> filteredSessions = new ArrayList<>();
		
		for (Session session : this.sessions) {
			LocalDateTime tmpDateTime = session.getStartTime();
			if (tmpDateTime.isBefore(givenTime)) {
				filteredSessions.add(session);
			}
		}

		return filteredSessions;
	}

	/**
	 * Return a list of session prior to the given <time> in descending order.
	 * 
	 * @param time The time to be counted up to
	 * @return a list of sessions up to a certain time in descending order
	 */
	public ArrayList<Session> getNSessionsDesc(LocalDateTime givenTime) {
		ArrayList<Session> sessions = this.getNSessionsAsc(givenTime);
		Collections.reverse(sessions);
		return sessions;
	}
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Session findSession(LocalDate startDate, LocalDate endDate) {
		for(Session tmpSession : this.sessions) {
			if(tmpSession.getStartTime().toLocalDate().equals(startDate) && tmpSession.getEndTime().toLocalDate().equals(endDate)) {
				return tmpSession;
			}
		}
		return null;
	}
}
