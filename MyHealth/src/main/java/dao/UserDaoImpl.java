package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.Record;
import model.User;

public class UserDaoImpl implements UserDao {
	private final String TABLE_NAME = "users";
	private model.User User;


	public UserDaoImpl() {
	}

	@Override
	public void setup() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		try (Connection connection = Database.getConnection();
			 Statement stmt = connection.createStatement();) {
			String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
					+ "firstname VARCHAR(10) NOT NULL," + "lastname VARCHAR(10) NOT NULL," + "password VARCHAR(8) NOT NULL," + "PRIMARY KEY (username))";
			stmt.executeUpdate(sql);
		}
	}

	@Override
	public User getUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, String.valueOf(password.hashCode()));

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				return null;
			}
		}
	}

	@Override
	public User createUser(String username, String firstname, String lastname, String password) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, username);
			stmt.setString(2, firstname);
			stmt.setString(3, lastname);
			stmt.setString(4, String.valueOf(password.hashCode()));

			stmt.executeUpdate();
			return new User(username, firstname, lastname, password);
		}
	}

	@Override
	public User updateUser(String firstname, String lastname, String username) throws SQLException {
		String sql = "update users set firstname='" + firstname + "' , lastname='" + lastname + "' WHERE username='" + username + "'  ";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.executeUpdate();
			return User;
		}
	}

	@Override
	public Record NewRecord(String date, String username, String weight, String temperature, String lowbp, String highbp, String Notes) {
		String sql = "INSERT INTO Records" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, String.valueOf(date));
			stmt.setString(2, username);
			stmt.setString(3, String.valueOf(weight));
			stmt.setString(4, String.valueOf(temperature));
			stmt.setString(5, String.valueOf(lowbp));
			stmt.setString(6, String.valueOf(highbp));
			stmt.setString(7, Notes);

			stmt.executeUpdate();
			return new Record(date, username, weight, temperature, lowbp, highbp);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<Record> view_record(String username) {
		ArrayList<Record> record = new ArrayList<Record>();
		String sql = "SELECT * FROM records" + " WHERE username ="+"'"+username+"'";
		try (Connection connection = Database.getConnection();
			 PreparedStatement stmt = connection.prepareStatement(sql);) {


			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {

					Record record_new = new Record(rs.getString("date"), rs.getString("weight"), rs.getString("Temperature"), rs.getString("lowbp"), rs.getString("highbp"), rs.getString("Notes"));
					record.add(record_new);
				}
				return record;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
