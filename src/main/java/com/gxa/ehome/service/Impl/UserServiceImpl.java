package com.gxa.ehome.service.Impl;

import com.gxa.ehome.dao.UserDao;
import com.gxa.ehome.pojo.User;
import com.gxa.ehome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao=null;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> SelectUserByPage(int page,int limit) throws SQLException, ClassNotFoundException {
        System.out.println("service层");
        return userDao.SelectUserByPage(page,limit);
    }

    @Override
    public void test() {
        System.out.println("测试");
    }
}
