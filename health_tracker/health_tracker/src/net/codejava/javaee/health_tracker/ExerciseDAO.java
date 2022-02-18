package net.codejava.javaee.health_tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ExerciseDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public ExerciseDAO(String jdbcURL, String jdbcUserbodyPart, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUserbodyPart;
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
	
	public boolean insertExercise(Exercise exercise) throws SQLException {
		String sql = "INSERT INTO exercise (date,mood,body_part) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, exercise.getDate());
		statement.setInt(2, exercise.getMood());
		statement.setString(3, exercise.getBodyPart());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Exercise> listAllExercises() throws SQLException {
		List<Exercise> listExercise = new ArrayList<>();
		
		String sql = "SELECT * FROM exercise";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("exercise_id");
			String date = resultSet.getString("date");
			int mood = resultSet.getInt("mood");
			String bodyPart = resultSet.getString("body_part");
			
			Exercise exercise = new Exercise(id, date, mood, bodyPart);
			listExercise.add(exercise);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listExercise;
	}
	
	public boolean deleteExercise(Exercise exercise) throws SQLException {
		String sql = "DELETE FROM exercise where exercise_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, exercise.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateExercise(Exercise exercise) throws SQLException {
		String sql = "UPDATE exercise SET date = ?, mood = ?, body_part = ?";
		sql += " WHERE exercise_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,exercise.getDate());
		statement.setInt(2, exercise.getMood());
		statement.setString(3, exercise.getBodyPart());
		statement.setInt(4, exercise.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	public Exercise getExercise(int id) throws SQLException {
        Exercise exercise = null;
        String sql = "SELECT * FROM exercise WHERE exercise_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
			String date = resultSet.getString("date");
			int mood = resultSet.getInt("mood");
			String bodyPart = resultSet.getString("body_part");
			exercise = new Exercise(id, date, mood, bodyPart);
        }

        resultSet.close();
        statement.close();

        return exercise;
    }}