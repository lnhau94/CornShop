package Entity;

import java.sql.*;

public class DAO {
    private static String connectURL = "jdbc:sqlserver://;" +
            "serverName=localhost;" +
            "databaseName=cornshop;" +
            "encrypt=true;trustServerCertificate=true";

    private static String DBuser = "admin";
    private static String DBpass = "123456";

/*

    private static String connectURL = "jdbc:sqlserver://;" +
            "serverName=database-1.czhlmlnnya7d.ap-southeast-1.rds.amazonaws.com;" +
            "databaseName=CornShop;" +
            "encrypt=true;trustServerCertificate=true";
    private static String DBuser = "admin";
    private static String DBpass = "1248163264128";


 */


    private static Connection connect;
    private static Statement stmt;

    public static ResultSet executeQuery(String sqlQuery){
        ResultSet result = null;
        try {
            connect = DriverManager.getConnection(connectURL,DBuser,DBpass);
            stmt= connect.createStatement();
            result = stmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void execute(String sqlQuery) throws SQLException {
        connect = DriverManager.getConnection(connectURL,DBuser,DBpass);
        stmt= connect.createStatement();
        stmt.execute(sqlQuery);
    }

    public static PreparedStatement getPrepareStatement(String sqlQuery) throws SQLException {
        connect = DriverManager.getConnection(connectURL,DBuser,DBpass);
        return connect.prepareStatement(sqlQuery);
    }

}
