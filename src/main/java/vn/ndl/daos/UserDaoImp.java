package vn.ndl.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.ndl.configs.DBConnectionMySQL;
import vn.ndl.models.*;

public class UserDaoImp implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public UserModel findByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username = ? ";
		UserModel user = null;
		try {
			new DBConnectionMySQL();
			conn = DBConnectionMySQL.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new UserModel();
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO user(username, password) VALUES (?,?)";
		try {
			new DBConnectionMySQL();
			conn = DBConnectionMySQL.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "SELECT * FROM user WHERE username = ?";
		try {
			new DBConnectionMySQL();
			conn = DBConnectionMySQL.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}
}