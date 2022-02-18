package net.codejava.javaee.health_tracker;


public class Food {
	protected int id;
	protected String date;
	protected String healthy;
	protected String name;

	public Food() {
	}

	public Food(int id) {
		this.id = id;
	}

	public Food(int id, String date, String healthy, String name) {
		this(date,healthy,name);
		this.id = id;
	}
	
	public Food(String date, String healthy, String name) {
		this.date = date;
		this.healthy = healthy;
		this.name = name;
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

	public String getHealthy() {
		return healthy;
	}
	public void  setHealthy(String healthy) {
		this.healthy = healthy;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name =name;
	}

}
