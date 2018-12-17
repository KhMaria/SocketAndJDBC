package jdbc;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleDriverManager {

    public Connection getConnection() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return null;
        }
        OracleDataSource dataSource = null;
        try {
            dataSource = new OracleDataSource();
            dataSource.setDatabaseName("sql.edu-netcracker.com");
            dataSource.setURL("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe");
            dataSource.setUser("TLT_1");
            dataSource.setPassword("TLT_1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            Connection connection = dataSource.getConnection();
            return connection;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
