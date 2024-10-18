package vn.ndl.daos;

import vn.ndl.models.UserModel;

public interface IUserDao {

	UserModel findByUsername(String username);
	void insert(UserModel user);
	boolean checkExistUsername(String username);
}
