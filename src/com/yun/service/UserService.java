package com.yun.service;

import com.yun.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yun.dao.UserDao;
import com.yun.pojo.User;
import com.yun.utils.UserUtils;

import java.io.File;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FileDao fileDao;

	public boolean addUser(User user) {
		user.setPassword(UserUtils.MD5(user.getPassword()));
		try {
			userDao.addUser(user);
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	public String getCountSize(String username) {
		String countSize = null;
		try {
			countSize = userDao.getCountSize(username);
		} catch (Exception e) {
			e.printStackTrace();
			return countSize;
		}
		return countSize;
	}

    public User findUser(User user) {
		try {
			user.setPassword(UserUtils.MD5(user.getPassword()));
			User exsitUser = userDao.findUser(user);
			return exsitUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	public User findUser(String username) {
		User user = null;
		try {
			user = userDao.findUserByUserName(username);
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
		return user;
	}

	public boolean findRepeatUsername(String username) throws Exception {
		try{
			if(username!=null){
				if(username.equals(userDao.findUserByUserName(username).getUsername())) {
					return false;
				}
			}

		}catch (Exception e){
			e.printStackTrace();
			return true;
		}
		return true;
	}
}
