package com.mysql.business;

import com.mysql.bean.HeiseiRider;
import com.mysql.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 业务处理，只设计对数据库的处理（增、删、改、查），不涉及数据库的连接
 */
public class RiderDAO {

    /**
     * org.apache.commons.dbutils: 用于简化 JDBC 对 sql 语句的预处理
     *     public class QueryRunner extends AbstractQueryRunner {}
     */
    private static QueryRunner queryRunner = new QueryRunner();

    //数据库表：kamen_rider

    /**
     * 添加数据
     */
    public static void insertInto(Integer id, String name, String videoTime, Integer riderNum){
        try {
            String sql = "INSERT INTO kamen_rider(id, name, video_time, rider_num) VALUES(?, ?, ?, ?)";
            Object[] params = new Object[]{id, name, videoTime, riderNum};
            queryRunner.update(JdbcUtils.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     */
    public static void update(Integer id, String videoTime){
        try {
            String sql = "UPDATE kamen_rider SET video_time=? WHERE id=?";
            Object[] params = new Object[]{videoTime, id};
            queryRunner.update(JdbcUtils.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部数据
     */
    public static void delete(int id){
        try {
            String sql = "DELETE FROM kamen_rider WHERE id=?";
            queryRunner.update(JdbcUtils.getConnection(), sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     * public BeanHandler(Class<? extends T> type): 将一行结果集转换成一个 javaBean 对象
     * public BeanListHandler(Class<? extends T> type): 将一行结果集转换成一个 javaBean 对象，然后多个对象封装到 List 集合中
     * 将数据结果集的每一行数据，封装为 JavaBean 对象，多个JavaBean对象封装到 List 集合中
     */
    public static void queryListBean(){
        try {
            String sql = "SELECT * FROM kamen_rider";
            List<HeiseiRider> lists = queryRunner.query(JdbcUtils.getConnection(), sql,
                    new BeanListHandler<HeiseiRider>(HeiseiRider.class));
            //foreach 输出
            for (HeiseiRider rider: lists){
                System.out.println(rider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     * public MapHandler() ：将一行结果集转换成一个 Map 对象
     * public MapListHandler(): 将一行结果集转换成一个 Map 对象，然后将多个 Map 对象封装到 List 集合中
     * 将数据结果集的给定一行的数据，封装到 Map 中
     */
    public static void queryMap(int id){
        try {
            String sql = "SELECT * FROM kamen_rider WHERE id=?";
            Map<String, Object> map = queryRunner.query(JdbcUtils.getConnection(), sql,
                    new MapHandler(),  id);

            //foreach 输出
            for (String key: map.keySet()){
                System.out.println(key + " ... " + map.get(key));
            }
            //
            //Set<String> keys = map.keySet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     * public ScalarHandler(): 单行单列，常与"SELECT COUNT(*) FROM kamen_rider"查询语句使用，返回一个 Object 对象
     */
    public static void queryCount(){
        try {
            String sql = "SELECT COUNT(*) FROM kamen_rider";
            long count = queryRunner.query(JdbcUtils.getConnection(), sql,
                    new ScalarHandler<>());
            System.out.println("There are " + count + " riders");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
