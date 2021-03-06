package application.usecases;

import java.util.ArrayList;
import java.util.HashMap;

import application.entities.DayStats;
import application.entities.Process;
import application.gateways.ProcessRepositoryGateway;
import application.gateways.StatisticsRepositoryGateway;

public class StatisticsRepository  {
	private DayStats dayStats;
	private int statsId;
	private final StatisticsRepositoryGateway statisticsRepositoryGateway;

	public StatisticsRepository(StatisticsRepositoryGateway statisticsRepositoryGateway) {
		
		this.statisticsRepositoryGateway = statisticsRepositoryGateway;
//		this.dayStats = dayStats;
		this.statisticsRepositoryGateway.populateUserData(this);
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
