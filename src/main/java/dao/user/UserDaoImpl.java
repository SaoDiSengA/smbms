package dao.user;
import dao.BaseDao;
import org.junit.Test;
import pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements  UserDao{
    public User getLoginUser(String userCode) {
        User user = null;
        String sql = "select * from smbms_user where userCode = ?";
        Connection connection = BaseDao.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object[] params = {userCode};
        try {
            ResultSet rs = BaseDao.executeQuery(connection,preparedStatement,sql, params);
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResources(connection,preparedStatement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
