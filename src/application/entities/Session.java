package application.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {
	private int sessionId;
	private int blockListId;
	private String startTime;
	private String endTime;
	private SessionStatus sessionStatus;

	public Session(int sessionId, int blockListId) {
		this.sessionStatus = SessionStatus.INITIALIZED;
		this.sessionId = sessionId;
		this.blockListId = blockListId;

		// getting today's date and time (keep in mind the possibility where
		// the user will manually change their time on the machine)
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.startTime = formatter.format(new Date());
		this.endTime = "";
	}

	public SessionStatus getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(SessionStatus sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public int getSessionId() {
		return sessionId;
	}

	public int getBlockListId() {
		return blockListId;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

}
