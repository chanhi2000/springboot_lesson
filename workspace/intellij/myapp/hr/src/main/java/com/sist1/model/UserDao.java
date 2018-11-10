package com.sist1.model;

import org.apache.log4j.Logger;
import java.sql.*;

public class UserDao {
    //
    //	IP: 211.238.142.102
    //	 * post: 1521
    //	 * SID: orcl
    //	 * sist/1224
    private final String url = "jdbc:oracle:thin:@211.238.142.102:1521:orcl";
    private final String user = "sist";
    private final String password = "1224";

    private static Logger log = Logger.getLogger(UserDao.class);
    /**
     * Oracle Connection
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * Create User into Database
     *
     * @param user
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void add(User user) throws ClassNotFoundException, SQLException {
        //---------------------------------------------
        // DB 연결
        //---------------------------------------------
        Connection c = this.getConnection();

        //---------------------------------------------
        // query
        //---------------------------------------------
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO users (u_id, name, password) VALUES (?,?,?) \n");
        log.debug("sql:\n" + sb.toString());
        //---------------------------------------------
        // 실행
        //---------------------------------------------
        PreparedStatement ps = c.prepareStatement(sb.toString());
        ps.setString(1, user.get_u_id());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        int flag = ps.executeUpdate();
        log.debug("flag:\n"+flag);
        //---------------------------------------------
        // 후 실행
        //---------------------------------------------
        ps.close();
        c.close();
    }

    public User get(String user_id) throws ClassNotFoundException, SQLException {
        //---------------------------------------------
        // DB 연결
        //---------------------------------------------
        Connection c = this.getConnection();

        //---------------------------------------------
        // query
        //---------------------------------------------
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM users WHERE u_id = ? \n");
        log.debug("sql:\n" + sb.toString());

        //---------------------------------------------
        // 실행
        //---------------------------------------------
        PreparedStatement ps = c.prepareStatement(sb.toString());
        ps.setString(1, user_id);
        User user = null;

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new User();
            user.set_u_id(  rs.getString("u_id")  );
            user.setPassword(  rs.getString("password")  );
            user.setName(  rs.getString("name")  );
        }
        log.debug("user:\n"+user.toString());
        //---------------------------------------------
        // 조회
        //---------------------------------------------
        return user;
    }
}
