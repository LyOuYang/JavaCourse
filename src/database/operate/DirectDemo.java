package database.operate;

import database.connection.connector.ConnectorDemo;
import org.junit.Test;

import java.sql.*;
public class DirectDemo {

    //增、删、改,dbutils原理

    /**
     * dbutils原理/插入方式
     *
     * @param con    连接器对象
     * @param sql    sql语句
     * @param params 需要插入的值
     */
    public void updateData(Connection con, String sql, Object... params) {
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            initParams(pre, params);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initParams(PreparedStatement pre, Object[] params) {
        for (int i = 0; i < params.length; i++) {
            try {
                pre.setObject(i + 1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询
     * @param con 连接器
     * @param sql sql语句
     * @param param Object数组
     * @return  返回ResultSet
     */
    public ResultSet queryData(Connection con, String sql, Object... param) {
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        try {
            pre = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initParams(pre, param);
        try {
            resultSet = pre.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Test
    public void testUpdateData() {
        Connection con = ConnectorDemo.getDatabaseConnection("E:\\IDEAProject\\JavaCourse\\src\\jdbc.properties");
        String sql = "insert into account values (?,?)";
        Object[] params = {"1253", 123};
        updateData(con, sql, params);
    }

    @Test
    public void testQueryData() throws SQLException {
        Connection con = ConnectorDemo.getDatabaseConnection("E:\\IDEAProject\\JavaCourse\\src\\jdbc.properties");
        String sql = "select * from account";
        ResultSet resultSet = queryData(con, sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + " ");
        }

        while (resultSet.next()) {
            System.out.println();
            for (int i = 1; i <= columnCount; i++)
                System.out.print(resultSet.getString(metaData.getColumnName(i)) + " ");
        }

    }

}
