package dao.user;

import pojo.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    //得到登录的用户
    public User getLoginUser(String userCode);
}
