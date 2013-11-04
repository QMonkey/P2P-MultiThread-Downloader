package p2p.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCUtil {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hci?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
    public static Connection getConnection() {
            Connection conn = null;
            try {
                    Class.forName(DB_DRIVER);
                    conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                    Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE,null,e);
            } catch (SQLException e) {
                    Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE,null,e);
            }
            return conn;
    }
    
    public static void release(ResultSet result,PreparedStatement pstat,Connection conn) {
            if(result != null) {
                    try {
                            result.close();
                    } catch (SQLException e) {
                            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE,null,e);
                    }
            }
            if(pstat != null) {
                    try {
                            pstat.close();
                    } catch (SQLException e) {
                            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE,null,e);
                    }
            }
            if(conn != null) {
                    try {
                            conn.close();
                    } catch (SQLException e) {
                            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE,null,e);
                    }
            }
    }
}
