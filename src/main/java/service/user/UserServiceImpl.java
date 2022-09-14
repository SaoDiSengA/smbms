package service.user;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.junit.Test;
import pojo.User;

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

    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "1234567");
        System.out.println(admin.getAddress());

    }
}

