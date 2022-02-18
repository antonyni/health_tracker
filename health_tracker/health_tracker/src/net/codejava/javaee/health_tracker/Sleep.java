package net.codejava.javaee.health_tracker;


public class Sleep {
	protected int id;
	protected String date;
	protected int mood;
	protected int rest;
	protected int how_long_hours;

	public Sleep() {
	}

	public Sleep(int id) {
		this.id = id;
	}

	public Sleep(int id, String date, int mood, int rest, int how_long_hours) {
		this(date,mood,rest,how_long_hours);
		this.id = id;
	}
	
	public Sleep(String date, int mood, int rest, int how_long_hours) {
		this.date = date;
		this.mood = mood;
		this.rest =rest;
		this.how_long_hours=how_long_hours;
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

	public int getRest() {
		return rest;
	}
	public void setRest(int rest) {
		this.rest =rest;
	}
	public int getHours() {
		return how_long_hours;
	}
	public void setHours(int how_long_hours) {
		this.how_long_hours =how_long_hours;
	}

}
