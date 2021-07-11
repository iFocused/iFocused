package application.usecases;

import java.util.*;

import com.calendarfx.model.Calendar;

import application.gateways.SessionRepositoryGateway;
import application.ui.CalendarFx.EntryWithBlockList;

public class SessionRepository {

	private Map<String, List<EntryWithBlockList<?>>> calendarsMap;
	private final SessionRepositoryGateway sessionRepositoryGateway;

	/**
	 * Default constructor for the session repository
	 */
	public SessionRepository() {
		this.sessionRepositoryGateway = null;
	}

	/**
	 * Constructor for the session repository
	 */
	public SessionRepository(SessionRepositoryGateway sessionRepositoryGateway) {
		this.calendarsMap = new HashMap<>();
		this.sessionRepositoryGateway = sessionRepositoryGateway;
		this.sessionRepositoryGateway.populateUserData(this);
	}

	public void exportCalendarsData() {
		this.sessionRepositoryGateway.saveUserData(this);
	}

	/**
	 * Adds a new session.
	 * 
	 * @param session The session to be added
	 */
	public void createCalendarEntry(Calendar calendar, EntryWithBlockList<?> entry) {
		entry.updateEntryTimeReps();

		if (this.calendarsMap.containsKey(calendar.getName())) {
			this.calendarsMap.get(calendar.getName()).add(entry);
		} else {
			this.calendarsMap.put(calendar.getName(), new ArrayList<>(Arrays.asList(entry)));
		}

	}

	/**
	 * Removes a calendar by name.
	 * 
	 * @param calendar that is to be removed
	 */
	public void removeCalendarByName(String calendarName) {
		this.calendarsMap.remove(calendarName);
	}

	/**
	 * Removes a calendar by object reference.
	 * 
	 * @param calendar that is to be removed
	 */
	public boolean removeEntryByReference(EntryWithBlockList<?> entry) {
		for (String calendarName : this.calendarsMap.keySet()) {
			for (EntryWithBlockList<?> e : this.calendarsMap.get(calendarName)) {
				if (e.equals(entry)) {
					this.calendarsMap.get(calendarName).remove(entry);
					if (this.calendarsMap.get(calendarName).isEmpty())
						this.calendarsMap.remove(calendarName);
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Return true iff the given <entry> was added successfully to the calendar with
	 * <calendarName>. If a calendar with such name does not exist, returns false.
	 * 
	 * @param calendarName
	 * @param entry
	 * @return
	 */
	public boolean addEntryToCalendar(String calendarName, EntryWithBlockList<?> entry) {
		if (this.calendarsMap.containsKey(calendarName)) {
			this.calendarsMap.get(calendarName).add(entry);
			return true;
		}
		return false;
	}

	/**
	 * Return a map of all the calendars in this repository corresponding to their
	 * ids
	 * 
	 * @return a map of all the calendars with their ids
	 */
	public Map<String, List<EntryWithBlockList<?>>> getCalendarsMap() {
		return this.calendarsMap;
	}

	/**
	 * Sets a new map of all the calendars in this repository corresponding to their
	 * ids
	 * 
	 */
	public void setCalendarsMap(Map<String, List<EntryWithBlockList<?>>> calendarsMap) {
		this.calendarsMap = calendarsMap;
	}

//	/**
//	 * Returns True iff the given <calendar> exists in the calendar map. Otherwise,
//	 * false is returned.
//	 * 
//	 * @param calendar that is to be checked against calendar map entries
//	 * @return whether the given <calendar> exists in the calendar map
//	 */
//	public boolean isCalendarExists(Calendar calendar) {
//		for (Calendar tmpCalendar : this.calendarsMap.values()) {
//			if (tmpCalendar.equals(calendar)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
