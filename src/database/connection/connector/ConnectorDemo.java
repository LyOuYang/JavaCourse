package database.connection.connector;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class ConnectorDemo {
    static HashMap<String, String> errorMap = new HashMap<>();

    public HashMap<String, String> getErrorMap() {
        return errorMap;
    }

    //获得连接器
    public static Connection getDatabaseConnection( String url, String user, String password){
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            errorMap.put("connectionError","连接数据库出错");
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 通过配置文件连接数据库
     * @param src 文件路径
     * @return Connection
     */
    public static  Connection getDatabaseConnection(String src){
        Properties pro = new Properties();
        try {
            pro.load(new FileInputStream(src));
        } catch (IOException e) {
            errorMap.put("proError","配置文件加载出错");
            e.printStackTrace();
        }
        String url = pro.getProperty("url");
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        Connection con = getDatabaseConnection(url, user, password);
        return con;
    }


    @Test
    public void getDatabaseConnection(){
        getDatabaseConnection("E:\\IDEAProject\\JavaCourse\\src\\jdbc.properties");
        if (getErrorMap().size()==0)
            System.out.println("数据库连接成功");
    }

}
