package application.usecases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import application.entities.DayStats;
import application.entities.Process;

public class StatisticsRepository implements Serializable {
	private DayStats dayStats;
	private int statsId;

	public StatisticsRepository(DayStats dayStats) {
		this.dayStats = dayStats;
	}

	public int createDayStats(int timeComputer, int timeStudying, int pomodoroSessions, int pointsEarned) {
		this.dayStats = new DayStats(timeComputer, timeStudying, pomodoroSessions, pointsEarned);
		int tmpId = this.statsId;
		this.statsId++;
		return tmpId;
	}

	public ArrayList<Process> getMostCommonApps() {
		return null;
	}

	public HashMap<Integer, Process> getBlockedProcesses() {
		return null;
	}

	public DayStats getDayStats() {
		return this.dayStats;
	}

	public void setDayStats(DayStats dayStats) {
		this.dayStats = dayStats;
	}

}
