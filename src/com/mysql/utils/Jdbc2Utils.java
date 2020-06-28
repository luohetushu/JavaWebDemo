package com.mysql.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc2Utils {

    private static final String BASE_PATH = "/Users/murongyunge/Desktop/IntelliJ/MySqlDemo";
    private static Properties prop = null;

    // 只在 JdbcUtils 加载时调用一次
    static {
        try {
            String filePath = BASE_PATH.replaceAll("/", File.separator);
            FileInputStream fis = new FileInputStream(new File(filePath,
                    File.separator + "res" + File.separator + "dbconfig.properties"));
            //要求资源文件 dbconfig.properties 与当前类在同一目录下
            //InputStream fis = JdbcUtils.class.getResourceAsStream("dbconfig.properties");
            //或者
            //InputStream fis = JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            //加载资源文件 dbconfig.properties
            prop = new Properties();
            prop.load(fis);
            //加载数据库驱动类
            Class.forName(prop.getProperty("mysql_driver"));
            System.out.println("数据库驱动加载成功");
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private Jdbc2Utils(){}

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(prop.getProperty("mysql_url"),
                prop.getProperty("mysql_user"), prop.getProperty("mysql_password"));
    }

}
