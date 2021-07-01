package application.usecases;

import com.calendarfx.model.Calendar;
import java.util.*;

import application.gateways.SessionRepositoryGateway;

public class SessionRepository {

	private Map<Integer, Calendar> calendarsMap;
	private int calendarId;
	private final SessionRepositoryGateway sessionRepositoryGateway;

	/**
	 * Constructor for the session repository
	 */
	public SessionRepository(SessionRepositoryGateway sessionRepositoryGateway) {
		this.calendarId = 0;
		this.calendarsMap = new HashMap<>();
		this.sessionRepositoryGateway = sessionRepositoryGateway;
		this.sessionRepositoryGateway.populateUserData(this);
	}

	/**
	 * Adds a new session.
	 * 
	 * @param session The session to be added
	 */
	public void createCalendar(Calendar calendar) {
		this.calendarsMap.put(calendarId, calendar);
		calendarId++;
	}

	/**
	 * Removes a calendar by id.
	 * 
	 * @param calendar that is to be removed
	 */
	public void removeCalendarById(int calendarId) {
		this.calendarsMap.remove(calendarId);
	}

	/**
	 * Removes a calendar by object reference.
	 * 
	 * @param calendar that is to be removed
	 */
	public void removeCalendarByReference(Calendar calendar) {
		for (int calendarId : this.calendarsMap.keySet()) {
			if (this.calendarsMap.get(calendarId).equals(calendar)) {
				this.calendarsMap.remove(calendarId, calendar);
				return;
			}
		}
	}

	/**
	 * Return a map of all the calendars in this repository corresponding to their
	 * ids
	 * 
	 * @return a map of all the calendars with their ids
	 */
	public Map<Integer, Calendar> getCalendarsMap() {
		return this.calendarsMap;
	}

	/**
	 * Returns True iff the given <calendar> exists in the calendar map. Otherwise,
	 * false is returned.
	 * 
	 * @param calendar that is to be checked against calendar map entries
	 * @return whether the given <calendar> exists in the calendar map
	 */
	public boolean isCalendarExists(Calendar calendar) {
		for (Calendar tmpCalendar : this.calendarsMap.values()) {
			if (tmpCalendar.equals(calendar)) {
				return true;
			}
		}
		return false;
	}
}
