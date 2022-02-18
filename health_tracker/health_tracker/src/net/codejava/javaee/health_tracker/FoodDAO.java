package net.codejava.javaee.health_tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FoodDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public FoodDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertFood(Food food) throws SQLException {
		String sql = "INSERT INTO food (date,healthy,name) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, food.getDate());
		statement.setString(2, food.getHealthy());
		statement.setString(3, food.getName());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Food> listAllFoods() throws SQLException {
		List<Food> listFood = new ArrayList<>();
		
		String sql = "SELECT * FROM food";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("food_id");
			String date = resultSet.getString("date");
			String healthy = resultSet.getString("healthy");
			String name = resultSet.getString("name");
			
			Food food = new Food(id, date, healthy, name);
			listFood.add(food);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listFood;
	}
	
	public boolean deleteFood(Food food) throws SQLException {
		String sql = "DELETE FROM food where food_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, food.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateFood(Food food) throws SQLException {
		String sql = "UPDATE food SET date = ?, healthy = ?, name = ?";
		sql += " WHERE food_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,food.getDate());
		statement.setString(2, food.getHealthy());
		statement.setString(3, food.getName());
		statement.setInt(4, food.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	public Food getFood(int id) throws SQLException {
        Food food = null;
        String sql = "SELECT * FROM food WHERE food_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
			String date = resultSet.getString("date");
			String healthy = resultSet.getString("healthy");
			String name = resultSet.getString("name");
			food = new Food(id, date, healthy, name);
        }

        resultSet.close();
        statement.close();

        return food;
    }
	/*public void initDB() throws SQLException{
		String sql = "CREATE TABLE food(\r\n"
				+ "food_id INT(10) NOT NULL AUTO_INCREMENT,\r\n"
				+ "date DATE NOT NULL,\r\n"
				+ "healthy varchar(3) NOT NULL,\r\n"
				+ "name varchar(40) NOT NULL,\r\n"
				+ "PRIMARY KEY(food_id,date)\r\n"
				+ ");\r\n"
				+ "INSERT INTO food(date,healthy,name)VALUES('2021-07-10','yes','chicken sandwich'),\r\n"
				+ "('2021-07-10','yes','chicken broccoli'),('2021-07-10','no','pizza'),('2021-07-11','yes','chicken sandwich'),('2021-07-11','yes','chicken spaghetti'),('2021-07-11','yes','teriyaki chicken')\r\n"
				+ ";\r\n"
				+ "CREATE TABLE exercise(\r\n"
				+ "exercise_id INT(10) NOT NULL AUTO_INCREMENT,\r\n"
				+ "date DATE NOT NULL,\r\n"
				+ "mood int(3) NOT NULL,\r\n"
				+ "body_part varchar(40) NOT NULL,\r\n"
				+ "PRIMARY KEY(exercise_id)\r\n"
				+ ");\r\n"
				+ "INSERT INTO exercise(date,mood,body_part)VALUES('2021-07-10',8,'back/bicep'),\r\n"
				+ "('2021-07-11', 9,'chest/tricep'),('2021-07-12',5,'legs')\r\n"
				+ ";\r\n"
				+ "CREATE TABLE sleep(\r\n"
				+ "sleep_id INT(10) NOT NULL AUTO_INCREMENT,\r\n"
				+ "date DATE NOT NULL,\r\n"
				+ "mood int(3) NOT NULL,\r\n"
				+ "rest int(3) NOT NULL,\r\n"
				+ "how_long_hours int(3) NOT NULL,\r\n"
				+ "PRIMARY KEY(sleep_id)\r\n"
				+ ");\r\n"
				+ "INSERT INTO sleep(date,mood,rest,how_long_hours)VALUES('2021-07-10',7,4,5),\r\n"
				+ "('2021-07-11', 6,8,10),('2021-07-12',9,8,10)\r\n"
				+ ";\r\n"
				+ "CREATE TABLE food_tally(\r\n"
				+ "tally_id INT(10) NOT NULL AUTO_INCREMENT,\r\n"
				+ "protein_grams INT(4),\r\n"
				+ "calories INT(5) NOT NULL,\r\n"
				+ "mood int(3) NOT NULL,\r\n"
				+ "food_id INT,\r\n"
				+ "date DATE NOT NULL,\r\n"
				+ "\r\n"
				+ "PRIMARY KEY(tally_id),\r\n"
				+ "CONSTRAINT FK_food_tally\r\n"
				+ "FOREIGN KEY (food_id,date) REFERENCES food(food_id,date)\r\n"
				+ "ON UPDATE CASCADE ON DELETE CASCADE\r\n"
				+ ");\r\n"
				+ "INSERT INTO food_tally(protein_grams,calories,mood,food_id,date)VALUES(70,700,6,1,'2021-07-10'),\r\n"
				+ "(65,500,7,2,'2021-07-10'),(30,700,8,3,'2021-07-10'),(70,700,4,4,'2021-07-11'),(50,500,6,5,'2021-07-11'),(70,300,6,6,'2021-07-11');";
		connect();
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.close();
		statement.close();	
		disconnect();
	}*/




}
