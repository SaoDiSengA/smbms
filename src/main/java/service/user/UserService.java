package service.user;

import pojo.User;

import java.sql.SQLException;

public interface UserService {
    public User login(String userCode, String userPassword) throws SQLException;
}
