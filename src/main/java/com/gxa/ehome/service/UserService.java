package com.gxa.ehome.service;

import com.gxa.ehome.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> SelectUserByPage(int page,int limit) throws SQLException, ClassNotFoundException;
    void test();
}
