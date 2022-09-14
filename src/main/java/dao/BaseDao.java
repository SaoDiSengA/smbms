package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//操作数据库的公共类
public class BaseDao {

    private static final String url;
    private static final String username;
    private static final String password;
    private static final String driver;

    //静态代码快，类加载就会执行
    static {
        //通过类加载器读取properties资源
        Properties properties = new Properties();
        InputStream resourceAsStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    //获取数据库的链接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            try {
                connection = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //编写查询公共方法
    public static ResultSet executeQuery(Connection connection,PreparedStatement preparedStatement,String sql,Object[] objects) throws SQLException {
        connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i+1,objects[i]);
        }
        return preparedStatement.executeQuery();
    }

    //编写曾删改公共方法
    public static int executeUpdate(Connection connection,PreparedStatement preparedStatement,String sql,Object[] objects) throws SQLException {
        connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i+1,objects[i]);
        }
        return preparedStatement.executeUpdate();
    }

    //关闭链接 释放资源
    public static boolean closeResources(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if (resultSet!=null){
            try {
                resultSet.close();
                //提示GC回收 resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
                //提示GC回收 preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection!=null){
            try {
                connection.close();
                //提示GC回收 connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
