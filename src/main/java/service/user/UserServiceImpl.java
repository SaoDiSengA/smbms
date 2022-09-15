package service.user;

import dao.BaseDao;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    //注入
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public User login(String userCode, String userPassword){
        User loginUser = userDao.getLoginUser(userCode);
        if (null != loginUser){
            if (!loginUser.getUserPassword().equals(userPassword)){
                loginUser = null;
            }
        }
        return loginUser;
    }

    public boolean updatePwd(String userCode, String newPassword) {
        Connection connection = BaseDao.getConnection();
        boolean b = userDao.updatePwd(connection, userCode, newPassword);
        BaseDao.closeResources(connection,null,null);
        return b;
    }

    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        boolean admin = userService.updatePwd("admin", "11111111");
        System.out.println(admin);

    }
}

