package com.mysql.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.rowset.serial.SerialBlob;
import java.beans.PropertyVetoException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BasicMySql {
    private static Connection con; //声明 Connection 对象
    private PreparedStatement pStmt;//声明预处理 PreparedStatement 对象

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // 协议：子协议：//目标IP地址：端口/数据库名
    private String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "root";

    public static void main(String[] args) {
        BasicMySql mySql = new BasicMySql();
        //连接数据库
        //con = mySql.getConnection();
        try {
            //con = JdbcUtils.getConnection();
            con = mySql.getC3P0Connection();
            System.out.println("数据库连接成功");
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }

        //创建新表
        //mySql.createTable();
        //创建新表
        //mySql.createMP3Table();

        //查询表数据
        //mySql.queryData();

        //增添表数据
        //mySql.addData();

        //更新表数据
        //mySql.updateData();

        //删除表数据
        //mySql.deleteData();

    }

    //建立返回值为 Connection 的方法
    private Connection getConnection(){
        //加载数据库驱动类
        try {
            Class.forName(JDBC_DRIVER); //调用 java.sql.Driver 实现子类的 static 静态块，进行驱动初始化
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //通过访问数据库的URL获取数据库连接对象
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return con;

    }

    /**
     * 使用 c3p0 连接池获取 java.sql.Connection 对象
     */
    private Connection getC3P0Connection() throws SQLException, PropertyVetoException {
        //创建池对象时，默认自动加载配置文件  //加载默认配置 default-config
        //ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //使用命名配置 //加载命名配置 name-config name="mysql-config"
        ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql-config");

        // 代码配置  有配置文件，代码配置可省略，不省略则覆盖
        // 设置连接参数
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        //设置池参数 不是必须配置
        dataSource.setInitialPoolSize(10);  //初始大小
        dataSource.setAcquireIncrement(5);  //增量
        dataSource.setMinPoolSize(3);  //最小空闲连接数
        dataSource.setMaxPoolSize(12);  //最大空闲连接数
        dataSource.setMaxStatements(20); //最大连接数
        dataSource.setMaxIdleTime(20);  //最大等待时间 秒

        Connection con = dataSource.getConnection();

        return con;
    }

    //创建新表
    private void createTable(){
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("create table kamen_rider(" +
                    "id int primary key auto_increment, " +
                    "name varchar(20), " +
                    "video_time varchar(50), " +
                    "rider_num int(20) " +
                    ")character set utf8 collate utf8_general_ci");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //创建存储 mp3 文件的数据表
    private void createMP3Table(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tab_mp3("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(20),"
                    + "data MEDIUMBLOB"
                    + ")character set utf8 collate utf8_general_ci";
            pStmt = con.prepareStatement(sql);
            pStmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static final String BASE_PATH = "/Users/murongyunge/Desktop/IntelliJ/MySqlDemo";
    // 往数据库表中存储 mp3
    private void saveMP3Data(){
        String filePath = BASE_PATH.replaceAll("/", File.separator);
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            //假设 res 文件夹中有 风雨如歌.mp3 文件
            fis = new FileInputStream(new File(filePath,
                    File.separator + "res" + File.separator + "风雨如歌.mp3"));
            baos = new ByteArrayOutputStream();
            int data = 0;
            while ((data = fis.read()) != -1){
                baos.write(data);
            }
            Blob blob = new SerialBlob(baos.toByteArray());

            //sql 插入语句
            String sql = "INSERT INTO tab_mp3(id, name, data) VALUES(?, ?, ?)";
            //
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, 1);
            pStmt.setString(2, "风雨如歌.mp3");
            pStmt.setBlob(3, blob);

            //
            pStmt.executeUpdate();

        } catch (SQLException | IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (baos != null){
                    baos.close();
                }
               if (fis != null){
                   fis.close();
               }
            } catch (IOException e){
                e.printStackTrace();
            }
        }



    }

    /**
     * java.sql.Statement
     *     public interface Statement extends Wrapper, AutoCloseable{}
     * java.sql.Connection
     *     public interface Connection  extends Wrapper, AutoCloseable {}
     *     声明方法：//确定了查询结果集的特性：是否滚动、是否敏感、是否更新
     *         Statement createStatement() throws SQLException;
     *             Statement state = con.createStatement();  //不滚动、不敏感、不更新
     *         Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException;
     *             resultSetType：
     *                ResultSet.TYPE_FORWARD_ONLY   //不滚动
     *                ResultSet.TYPE_SCROLL_INSENSITIVE //可滚动，不敏感，结果集数据不会随数据库数据变化而变化
     *                ResultSet.TYPE_SCROLL_SENSITIVE //可滚动，敏感，结果集数据随数据库数据变化而变化 //没有数据库驱动会实现它
     *         resultSetConcurrency：
     *                ResultSet.CONCUR_READ_ONLY  //结果集只读，
     *                ResultSet.CONCUR_UPDATABLE  //结果集可更新，对结果集更新反向影响数据库
     */

    /**
     * 预处理：java.sql.PreparedStatement：
     *           public interface PreparedStatement extends Statement {}
     *           优点：防止 sql 攻击
     *                提高代码可读性、可维护性、
     *                提高效率
     */

    //查询表数据
    private void queryData(){
        ResultSet result = null;
        try {
            //mysql查询语句
            String sql = "select * from student";
            //Statement state = con.createStatement();
            //result = state.executeQuery(sql);
            pStmt = con.prepareStatement(sql);
            result = pStmt.executeQuery();
            while (result.next()){
                System.out.println("Kamen Rider " + result.getString("name") + " is "
                        + result.getString("age") + " years old, a " + result.getString("sex") + " rider");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                if (result != null){
                    result.close();
                }
                if (pStmt != null){
                    pStmt.close();
                }
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    //用户登录
    private boolean userLogin(String username, String password){
        ResultSet result = null;
        try {
            //mysql查询语句
            String sql = "SELECT * FROM student WHERE username=? AND password=?";
            pStmt = con.prepareStatement(sql);
            //赋值
            pStmt.setString(1, username); //给第一个 ？ 处赋值
            pStmt.setString(2, password); //给第一个 ？ 处赋值
            //查询
            result = pStmt.executeQuery();
            return result.next();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                if (result != null){
                    result.close();
                }
                if (pStmt != null){
                    pStmt.close();
                }
                if (con != null){
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    //添加表数据
    private void addData(){
        try {
//            pStmt = con.prepareStatement("insert into student(name, sex, age, birthday) " +
//                    "values('Decade', '男', '23', '1988-11-12')");
//            pStmt.executeUpdate();
            pStmt = con.prepareStatement("insert into student(id, name, sex, age, birthday) " +
                    "values(10, 'Poppy', '女', '19', '1993-08-15') on duplicate key update age = '20'");
            pStmt.executeUpdate();
            pStmt = con.prepareStatement("insert into student(id, name, sex, age, birthday) " +
                    "values(15, 'Wizard', '男', '22', '1990-09-09') on duplicate key update birthday = '1990-09-19'");
            pStmt.executeUpdate();
//            pStmt = con.prepareStatement("insert into student(name, sex, age, birthday) " +
//                    "values('Drive', '男', '24', '1992-06-16')");
//            pStmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //批量添加数据 需要数据库开启批处理：rewriteBatchedStatements=true
    private void addBatchedData(){
        try {
            String sql = "INSERT INTO student(id, name, sex, age, birthday) VALUES(?, ?, ?, ?, ?)";
            pStmt = con.prepareStatement(sql);
            for (int i = 0; i < 10; i++) {
                pStmt.setInt(1, i + 1);
                pStmt.setString(2, "Rider " + i);
                pStmt.setString(3, (i % 2 == 0) ? "男" : "女");
                pStmt.setInt(4, 20 + i);
                String birth = "199" + i + "-" + (i + 1) + "-" + "2" + i;
                java.util.Date uDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
                pStmt.setDate(5, new java.sql.Date(uDate.getTime()));
                //添加批处理
                pStmt.addBatch();
            }
            //执行批处理
            pStmt.executeBatch();
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 处理事务：java.sql.Connection 方法
     *     开启事务：void setAutoCommit(boolean autoCommit) throws SQLException; //是否自动提交事务，默认为 true，表每执行一条 aql 语句皆为单独的事务
     *                                                                         //con.setAutoCommit(false)； //表示开启事务
     *     结束事务：void commit() throws SQLException;  //提交结束事务
     *     回滚事务：void rollback() throws SQLException; //回滚结束事务
     * 注：同一个事务中，所有的 sql 语句执行都使用同一个 Connection 对象
     */
    private void setTransaction(){
        try {
            //开启事务
            con.setAutoCommit(false);
            //执行多条 sql 语句
            addTransactionData(con, 2);
            addTransactionData(con, 3);
            addTransactionData(con, 5);
            //提交事务
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
            try {
                if (con != null){
                    //回滚事务
                    con.rollback();
                }
            } catch (SQLException exc){
                exc.printStackTrace();
            }
        } finally {
            try {
                if (con != null){
                    //关闭连接
                    con.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //使用事务添加数据
    //同一个事务中，所有的 sql 语句执行都使用同一个 Connection 对象
    private void addTransactionData(Connection con, int i){
        try {
            String sql = "INSERT INTO student(id, name, sex, age, birthday) VALUES(?, ?, ?, ?, ?)";
            pStmt = con.prepareStatement(sql);

            //赋值
            pStmt.setInt(1, i + 1);
            pStmt.setString(2, "Rider " + i);
            pStmt.setString(3, (i % 2 == 0) ? "男" : "女");
            pStmt.setInt(4, 20 + i);
            String birth = "199" + i + "-" + (i + 1) + "-" + "2" + i;
            java.util.Date uDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            pStmt.setDate(5, new java.sql.Date(uDate.getTime()));

            //执行批处理
            pStmt.executeUpdate();
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        }
    }


    //更新表数据
    private void updateData(){
        try {
           pStmt = con.prepareStatement("update student set age = '23' where name = 'Decade'");
           pStmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //删除表数据
    private void deleteData(){
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from student where name = 'Decade'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
