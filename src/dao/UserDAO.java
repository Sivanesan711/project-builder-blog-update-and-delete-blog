package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {

	public int signUp(User user) throws Exception {
		String sql = "INSERT INTO BLOG(email, password)VALUES(?,?)";

		int result = 0;
		try
		{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,user.getEmail());
			preparedStatement.setString(2,user.getPassword());
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	public boolean loginUser(User user) throws Exception {
		boolean status = false;
		try{
			ConnectionManager cm = new ConnectionManager();
			Connection con = cm.getConnection();
		    PreparedStatement preparedStatement = con.prepareStatement("select * from blog where email = ? and password = ? ");
		
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return status;
	}

}
