package application.usecases;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;

import application.entities.PomodoroSession;
import application.entities.SessionStatus;

public class PointEligibility {
	private Date timeSinceLastPt;
	private Date timeAppRunning;
	private TODOList todoList;
	private PomodoroRepository pomodoroRepo;
	private SessionRepository sessionRepo;

	public PointEligibility(TODOList todoList, SessionRepository sessionRepo, PomodoroRepository pomodoroRepo)
			throws ParseException {
		this.todoList = todoList;
		this.sessionRepo = sessionRepo;
		this.pomodoroRepo = pomodoroRepo;
		this.timeAppRunning = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
				.parse(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
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
		Date pomodoroTime, currTime, newCurrTime = null;

		try {
			pomodoroTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(recentPomodoroSession.getEndTime());
			currTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
					.parse(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

			long differenceInMins = TimeUnit.MILLISECONDS.toMinutes(currTime.getTime() - pomodoroTime.getTime()) % 60;

			newCurrTime = DateUtils.addMinutes(newCurrTime, (int) -differenceInMins); // subtract mins
			if (pomodoroTime.after(newCurrTime)) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
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
			try {
				this.timeSinceLastPt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
						.parse(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
				return true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
