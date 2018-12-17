package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcApplication {

    public static void main(String[] args) throws SQLException {


        OracleDriverManager oracleDriverManager = new OracleDriverManager();
        Connection oracleConnection = oracleDriverManager.getConnection();

        SimpleOracleExample simpleOracleExample = new SimpleOracleExample(oracleConnection);
        simpleOracleExample.selectExample();
        simpleOracleExample.insertExample();
        simpleOracleExample.updateExample();
        simpleOracleExample.deleteExample();
        //simpleOracleExample.autoCommitExample();

        PostgresDriverManager driverManager = new PostgresDriverManager();
        //Connection connection = driverManager.openPostgresConnection();
        //SimplePostgresExample simpleExample = new SimplePostgresExample(connection);
        //simpleExample.selectExample();
        //simpleExample.insertExample();
        //simpleExample.updateExample();
        //simpleExample.deleteExample();
        //simpleExample.autoCommitExample();
    }
}


