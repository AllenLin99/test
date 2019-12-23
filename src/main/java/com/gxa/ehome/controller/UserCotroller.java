package com.gxa.ehome.controller;

import com.gxa.ehome.pojo.User;
import com.gxa.ehome.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;
import java.util.List;

@Controller(value = "userController")
public class UserCotroller {
    @Autowired
    @Qualifier("userService")
    private UserService userService = null;
    public void showUsersByPage() {
        int page=2;
        int limit=5;
        try {
            List<User> userList = userService.SelectUserByPage(page, limit);
            System.out.println(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //userService.test();
    }
}
