package com.gxa.ehome.dao.Impl;

import com.gxa.ehome.dao.UserDao;
import com.gxa.ehome.pojo.User;
import com.gxa.ehome.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("dbUtilBean")
    private DBUtil dbUtil=null;
//    @Autowired
//    @Qualifier("userBean")
//    private User user=null;
     //声明一个用于添加数据的集合
    List<User> userList = new ArrayList<>();


    @Override
    public List<User> SelectUserByPage(int page,int limit) throws SQLException, ClassNotFoundException {
        System.out.println("dao方法");
        String sql="select * from users limit ?,?";
        PreparedStatement preparedStatement=dbUtil.getPreparedStatement(sql);
        preparedStatement.setInt(1,(page-1)*limit );
        preparedStatement.setInt(2,limit );
        ResultSet resultSet = dbUtil.execQuery(preparedStatement);
        while (resultSet.next()){
            User user=new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setPwd(resultSet.getString(3));
            userList.add(user);
        }
        dbUtil.closeAll();
        return userList;
    }

    public DBUtil getDbUtil() {
        return dbUtil;
    }

    public void setDbUtil(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }
}
