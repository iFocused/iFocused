package application.ui.CalendarFx;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import application.entities.BlockList;

public class EntryWithBlockList<T> extends Entry<T> {
	private BlockList blockList;
	private String startTimeRep;
	private String endTimeRep;
	private String startDateRep;
	private String endDateRep;

	public EntryWithBlockList() {
	}

	public EntryWithBlockList(String title) {
		super(title);
		this.startTimeRep = this.getStartTime().toString();
		this.endTimeRep = this.getEndTime().toString();
		this.startDateRep = this.getStartDate().toString();
		this.endDateRep = this.getEndDate().toString();

	}

	/**
	 * @return the startTimeRep
	 */
	public String getStartTimeRep() {
		return startTimeRep;
	}

	/**
	 * @return the endTimeRep
	 */
	public String getEndTimeRep() {
		return endTimeRep;
	}

	/**
	 * @param endTimeRep the endTimeRep to set
	 */
	public void setEndTimeRep(String endTimeRep) {
		this.endTimeRep = endTimeRep;
	}

	/**
	 * @return the entryStartDateRep
	 */
	public String getStartDateRep() {
		return startDateRep;
	}

	/**
	 * @param startDateRep the entryStartDateRep to set
	 */
	public void setStartDateRep(String startDateRep) {
		this.startDateRep = startDateRep;
	}

	/**
	 * @return the entryEndDateRep
	 */
	public String getEndDateRep() {
		return endDateRep;
	}

	/**
	 * @param endDateRep the entryEndDateRep to set
	 */
	public void setEndDateRep(String endDateRep) {
		this.endDateRep = endDateRep;
	}

	/**
	 * @param startTimeRep the startTimeRep to set
	 */
	public void setStartTimeRep(String startTimeRep) {
		this.startTimeRep = startTimeRep;
	}

	public EntryWithBlockList(String title, Interval interval) {
		super(title, interval);
	}

	public BlockList getBlockList() {
		return this.blockList;
	}

	public void setBlockList(BlockList blockList) {
		this.blockList = blockList;
	}

	public void updateEntryTimeReps() {
		this.startTimeRep = this.getStartTime().toString();
		this.endTimeRep = this.getEndTime().toString();
		this.startDateRep = this.getStartDate().toString();
		this.endDateRep = this.getEndDate().toString();

	}

}
