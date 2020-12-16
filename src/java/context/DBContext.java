package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContext {

    private final String serverName = "localhost";
    private final String dbname = "digital";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "12345678";
    private static DBContext instance;

    public static DBContext getInstance() throws Exception {
        if (instance == null) {
            instance = new DBContext();
        }
        return instance;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://"
                + serverName + ":"
                + portNumber + ";databaseName="
                + dbname, userID, password);
        return conn;

        //bên dưới là kết nối kiểu authen dùng cho máy nhà trường
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        Connection conn = DriverManager.getConnection("jdbc:sqlserver://"
//                + serverName + ":"serverName
//                + portNumber + ";databaseName="
//                + dbname +";integratedSecurity=true;");
//        return conn;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws Exception {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
