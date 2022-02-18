package net.codejava.javaee.health_tracker;


public class Exercise {
	protected int id;
	protected String date;
	protected int mood;
	protected String bodyPart;

	public Exercise() {
	}

	public Exercise(int id) {
		this.id = id;
	}

	public Exercise(int id, String date, int mood, String bodyPart) {
		this(date,mood,bodyPart);
		this.id = id;
	}
	
	public Exercise(String date, int mood, String bodyPart) {
		this.date = date;
		this.mood = mood;
		this.bodyPart = bodyPart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMood() {
		return mood;
	}
	public void  setMood(int mood) {
		this.mood = mood;
	}

	public String getBodyPart() {
		return this.bodyPart;
	}
	public void setBodyPart(String bodyPart) {
		this.bodyPart =bodyPart;
	}

}
