package ctrlpkg;

import java.sql.*;

public class DBConnection {
    private static DBConnection instance = null;
    private Connection cn;
    private Statement st;
    private ResultSet rs;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null)
            instance = new DBConnection();
        return instance;
    }

    public void getConnection(String url, String username, String password) throws SQLException {
        cn = DriverManager.getConnection(url, username, password);
    }

    public void closeConnection() throws SQLException {
        cn.close();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        st = cn.createStatement();
        rs = st.executeQuery(query);
        return rs;
    }

    public void execute(String query) throws SQLException {
        st = cn.createStatement();
        st.execute(query);
    }
}
