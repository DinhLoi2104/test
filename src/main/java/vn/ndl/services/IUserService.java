package vn.ndl.services;

import vn.ndl.models.UserModel;

public interface IUserService {
	UserModel login (String username, String password);
	UserModel findByUsername (String username);
	void insert(UserModel user);
	boolean register(String username, String password);
	boolean checkExistUsername(String username);
}
