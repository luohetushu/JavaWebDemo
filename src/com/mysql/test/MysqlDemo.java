package com.mysql.test;

import com.mysql.business.RiderDAO;
import com.mysql.utils.JdbcUtils;

import java.sql.SQLException;

public class MysqlDemo {
    public static void main(String[] args) {

        try {
            //开启事务
            JdbcUtils.startTransaction();

            //添加sql
            //RiderDAO.insertInto(1, "W", "2010-09-01", 3);
            //RiderDAO.insertInto(2, "OOO", "2011-09-01", 2);
            //RiderDAO.insertInto(3, "Fourze", "2012-09-01", 2);

            //查询 sql
            //RiderDAO.queryListBean();
            //RiderDAO.queryMap(2);
            RiderDAO.queryCount();

            //提交
            JdbcUtils.commitTransaction();

        } catch (Exception e){
            e.printStackTrace();
            //回滚
            JdbcUtils.rollbackTransaction();
        }

    }
}
