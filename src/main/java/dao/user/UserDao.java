package dao.user;

import pojo.User;
import java.sql.Connection;

public interface UserDao {
    //得到登录的用户
    public User getLoginUser(String userCode);

    //修改用户密码
    public boolean updatePwd(Connection connection,String userCode,String newPassword);
}
