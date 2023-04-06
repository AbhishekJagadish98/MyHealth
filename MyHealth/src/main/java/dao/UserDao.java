package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Record;
import model.User;

public interface UserDao {
	void setup() throws SQLException, ClassNotFoundException;
	User getUser(String username, String password) throws SQLException;

	User createUser(String username, String firstname, String lastname, String password) throws SQLException;

    User updateUser( String firstname, String lastname,String username) throws SQLException;

	Record NewRecord(String date, String username, String weight, String temperature, String lowbp, String highbp, String Notes)throws SQLException;

	ArrayList<Record> view_record(String username) ;

}
