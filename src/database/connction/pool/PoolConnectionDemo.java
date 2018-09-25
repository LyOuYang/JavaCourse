package database.connction.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PoolConnectionDemo {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public ComboPooledDataSource getDataSource() {
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?&useSSL=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("hong1996");
        return dataSource;
    }

    public Connection getDatabaseConnection() {
        Connection con = null;
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?&useSSL=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("hong1996");
        //初始池大小
        dataSource.setInitialPoolSize(20);
        //每次增量
        dataSource.setAcquireIncrement(5);
        //最大池中连接数目
        dataSource.setMaxPoolSize(30);
        //最小池中连接数目
        dataSource.setMinPoolSize(5);
        //最大等待连接时间
        dataSource.setMaxIdleTime(60);
        //失败连接尝试次数
        dataSource.setAcquireRetryAttempts(3);
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Test
    public void testGetDatabaseConnection() throws SQLException {
        Connection con = getDatabaseConnection();
        String sql = "insert into account values (?,?)";
        PreparedStatement pro = null;
        try {
            pro = con.prepareStatement(sql);
            pro.setString(1, "1111111111");
            pro.setDouble(2, 20);
            pro.executeUpdate();
        } catch (SQLException e) {
            pro.executeUpdate();
        }
    }
}