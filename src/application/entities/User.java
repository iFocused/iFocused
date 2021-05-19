package application.entities;

import java.util.SimpleTimeZone;

public class User {
	private int points;
	private String username;
	private BlockMode blockMode;
	private SimpleTimeZone timeZone;

	public User(String username, SimpleTimeZone timeZone) {
		this.points = 0;
		this.blockMode = BlockMode.SIMPLE;
		this.username = username;
		this.timeZone = timeZone;
	}

	public void addPoint() {
		this.points++;
	}

	public int getPoints() {
		return this.points;
	}

	public String getUsername() {
		return this.username;
	}

	public BlockMode getBlockMode() {
		return this.blockMode;
	}

	public SimpleTimeZone getTimeZone() {
		return this.timeZone;
	}

	public void setPoints(int newPoints) {
		this.points = newPoints;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public void setBlockMode(BlockMode newBlockMode) {
		this.blockMode = newBlockMode;
	}

	public void setTimeZone(SimpleTimeZone newTimeZone) {
		this.timeZone = newTimeZone;
	}

}