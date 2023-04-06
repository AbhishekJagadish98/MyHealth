package model;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;

/** This class is where Model class connects to the sql statements
 /*
 * Classname : Model
 *
 * Version information : 1.0.0
 *
 * Date : 23-10-2022
 *
 * By Abhishek Jagadish s3911506
 */

public class Model {
	private UserDao userDao;
	private User currentUser;
	
	public Model() {
		userDao = new UserDaoImpl();
	}
	
	public void setup() throws SQLException, ClassNotFoundException {
		userDao.setup();
	}
	public UserDao getUserDao() {
		return userDao;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}
}
