package database.operate;

import beans.Student;
import database.connction.pool.PoolConnectionDemo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UseDBUtils<pubilc> {
    PoolConnectionDemo poolConnection = new PoolConnectionDemo();

    public void dbUtilsUpdate() {
        QueryRunner qr = new QueryRunner(poolConnection.getDataSource());
        String sql = "insert into account values (?,?)";
        Object[] param = {"zz", 1};
        try {
            int update = qr.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List dbUtilsBeanListQuery() {
        QueryRunner qr = new QueryRunner(poolConnection.getDataSource());
        String sql = "select * from account";
        List<Student> query = null;
        try {
            query = qr.query(sql, new BeanListHandler<>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public Map dbUtilsMapHander() {
        QueryRunner qr = new QueryRunner(poolConnection.getDataSource());
        String sql = "select * from account";
        Map map = null;
        try {
            map = qr.query(sql, new MapHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }


    public List<Map<String, Object>> dbUtilsMapListHandier() {
        QueryRunner qr = new QueryRunner(poolConnection.getDataSource());
        String sql = "select * from account";
        List<Map<String, Object>> query = null;
        try {
            query = qr.query(sql, new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }


    @Test
    public void testDbUtilsUpdate() {
        dbUtilsUpdate();
    }

    @Test
    public void testDbUtilsBeanListQuery() {
        List list = dbUtilsBeanListQuery();
        System.out.println(list.toString());
    }

    @Test
    public void testDbUtilsMapHandier() {
        Map map = dbUtilsMapHander();
        System.out.println(map.toString());
    }

    @Test
    public void testDbUtilsMapListHandier() {
        List<Map<String, Object>> maps = dbUtilsMapListHandier();
        System.out.println(maps.toString());
    }

}
