package application.entities;

import java.time.LocalDateTime;

public class Session {
	private int sessionId;
	private int blockListId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private SessionStatus sessionStatus;

	public Session(int sessionId, int blockListId) {
		this.sessionStatus = SessionStatus.INITIALIZED;
		this.sessionId = sessionId;
		this.blockListId = blockListId;

		// getting today's date and time (keep in mind the possibility where
		// the user will manually change their time on the machine)
		this.startTime = LocalDateTime.now();
		this.endTime = null;
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

}
