package application.ui.CalendarFx;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import application.entities.BlockList;

public class EntryWithBlockList<T> extends Entry<T> {
	private BlockList blockList;

	public EntryWithBlockList() {}

	public EntryWithBlockList(String title) {
		super(title);
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

}
