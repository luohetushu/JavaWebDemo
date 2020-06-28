package com.mysql.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    //获取 c3p0 数据库连接池对象，使用配置文件 默认 配置 default-config
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //java.lang.ThreadLocale<T> 类：
    //当有多个线程在进行各自信息处理，很有可能出现线程信息处理交错的情况，此时就需要使用 ThreadLocale 类给各个线程标记
    //创建 ThreadLocal 对象，给 java.sql.Connection 对象标记
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    /**
     * 返回 c3p0 数据库连接池对象
     * @return
     */
    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    /**
     * 返回 通过连接池对象获取到的连接对象
     * @return
     */
    public static Connection getConnection() throws SQLException {
        Connection con = threadLocal.get();
        if (con == null){
            con = dataSource.getConnection();
            threadLocal.set(con);
            System.out.println("数据库首次连接");
        }
        return con;
    }

    /**
     * 开启事务
     */
    public static void startTransaction(){
        try {
            // 获取连接
            Connection con = getConnection();
            //开启事务
            con.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction(){
        // 获取连接
        Connection con = threadLocal.get();
        try {
            if (con != null){
                con.commit();
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (con != null){
                    con.close();
                    threadLocal.remove();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction(){
        // 获取连接
        Connection con = threadLocal.get();
        try {
            if (con != null){
                con.rollback();
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (con != null){
                    con.close();
                    threadLocal.remove();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放连接：判断是否是事务专用连接，是则不关闭；不是则关闭
     */
    public void releaseConnection(Connection con) throws SQLException {
        if (threadLocal.get() == null){
            con.close();
        }
        if (threadLocal.get() != con){
            con.close();
        }
    }

}
