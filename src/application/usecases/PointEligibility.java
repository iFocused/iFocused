package application.usecases;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import application.entities.PomodoroSession;
import application.entities.SessionStatus;

public class PointEligibility {
	private LocalDateTime timeSinceLastPt;
	private LocalDateTime timeAppRunning;
	private TODOList todoList;
	private PomodoroRepository pomodoroRepo;
	private SessionRepository sessionRepo;

	public PointEligibility(TODOList todoList, SessionRepository sessionRepo, PomodoroRepository pomodoroRepo) {
		this.todoList = todoList;
		this.sessionRepo = sessionRepo;
		this.pomodoroRepo = pomodoroRepo;
		this.timeAppRunning = LocalDateTime.now();
	}

	private boolean checkTaskForCompletion() {
		return todoList.getCurrentTasks().isEmpty() && !todoList.getCompletedTasks().isEmpty();
	}

	private boolean checkPomodoroForCompletion() {
		// check if a pomodoro session was completed
		PomodoroSession recentPomodoroSession = pomodoroRepo.getMostRecentPomodoroSession();
		if (recentPomodoroSession == null
				|| !recentPomodoroSession.getSession().getSessionStatus().equals(SessionStatus.FINISHED)) {
			return false;
		}

		// check if the pomodoro session was completed after the last time a point was
		// given
		LocalDateTime pomodoroTime, currTime, newCurrTime = null;

		pomodoroTime = recentPomodoroSession.getEndTime();
		currTime = LocalDateTime.now();

		long differenceInMins = currTime.until(pomodoroTime, ChronoUnit.MINUTES);

		newCurrTime = newCurrTime.minusMinutes((int) differenceInMins); // subtract mins
		if (pomodoroTime.isAfter(newCurrTime)) {
			return true;
		}

		return false;

	}

	/* TODO */
	private boolean wasBlockListModified() {
		return false;
	}

	/* TODO */
	private boolean wasScheduleSlotCompleted() {
		return false;
	}

	/* TODO */
	private long getAppRunningTime() {
		return 0;
	}

	public boolean isUserEligible() {
		int criteriaSatisfied = 0;

		if (checkPomodoroForCompletion()) {
			criteriaSatisfied++;
		}

		if (checkPomodoroForCompletion()) {
			criteriaSatisfied++;
		}

		if (wasBlockListModified()) {
			criteriaSatisfied++;
		}

		if (wasScheduleSlotCompleted()) {
			criteriaSatisfied++;
		}

		if (getAppRunningTime() >= 10 && criteriaSatisfied >= 2) {
			this.timeSinceLastPt = LocalDateTime.now();
			return true;
		}
		return false;
	}

}
