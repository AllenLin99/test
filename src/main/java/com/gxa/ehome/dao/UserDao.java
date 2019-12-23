package com.gxa.ehome.dao;

import com.gxa.ehome.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> SelectUserByPage(int page,int limit) throws SQLException, ClassNotFoundException;
}
