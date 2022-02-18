package net.codejava.javaee.health_tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SleepDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public SleepDAO(String jdbcURL, String jdbcUserbodyPart, String jdbcPassword) {
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
	
	public boolean insertSleep(Sleep sleep) throws SQLException {
		String sql = "INSERT INTO sleep (date,mood,rest,how_long_hours) VALUES (?, ?, ?,?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, sleep.getDate());
		statement.setInt(2, sleep.getMood());
		statement.setInt(3, sleep.getRest());
		statement.setInt(4, sleep.getHours());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Sleep> listAllSleeps() throws SQLException {
		List<Sleep> listSleep = new ArrayList<>();
		
		String sql = "SELECT * FROM sleep";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("sleep_id");
			String date = resultSet.getString("date");
			int mood = resultSet.getInt("mood");
			int rest = resultSet.getInt("rest");
			int how_long_hours = resultSet.getInt("how_long_hours");
			
			Sleep sleep = new Sleep(id, date, mood, rest,how_long_hours);
			listSleep.add(sleep);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listSleep;
	}
	
	public boolean deleteSleep(Sleep sleep) throws SQLException {
		String sql = "DELETE FROM sleep where sleep_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, sleep.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateSleep(Sleep sleep) throws SQLException {
		String sql = "UPDATE sleep SET date = ?, mood = ?, rest = ?, how_long_hours = ?";
		sql += " WHERE sleep_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1,sleep.getDate());
		statement.setInt(2, sleep.getMood());
		statement.setInt(3, sleep.getRest());
		statement.setInt(4, sleep.getHours());
		statement.setInt(4, sleep.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	public Sleep getSleep(int id) throws SQLException {
        Sleep sleep = null;
        String sql = "SELECT * FROM sleep WHERE sleep_id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
			String date = resultSet.getString("date");
			int mood = resultSet.getInt("mood");
			int rest = resultSet.getInt("rest");
			int how_long_hours = resultSet.getInt("how_long_hours");
			sleep = new Sleep(id, date, mood, rest,how_long_hours);
        }

        resultSet.close();
        statement.close();

        return sleep;
    }}