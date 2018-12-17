package jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class SimpleOracleExample {
    private Connection connection;

    public SimpleOracleExample(Connection connection) {
        this.connection = connection;
    }

    public void selectExample() throws SQLException {
        String query = "SELECT * FROM emp";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            int empno = resultSet.getInt("empno");
            String ename = resultSet.getString("ename");
            String job = resultSet.getString("job");
            int mgr = resultSet.getInt("mgr");
            Date date = resultSet.getDate("hiredate");
            System.out.println("Employee " + empno + " " + ename + " " + job + " " + mgr + " " + date);
        }
        System.out.println("--- ALL ROWS ARE FETCHED ---");
    }

    public void insertExample() throws SQLException {
        try {
            String query = "INSERT INTO pers (pers_id, depart_id, firstname, lastname, middlename, hire_date, salary)" +
                    " VALUES (121, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Jhon");
            preparedStatement.setString(3, "Snow");
            preparedStatement.setString(4, "Petrovich");

            String strDate = "13.08.06";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
            java.util.Date date = simpleDateFormat.parse(strDate);
            java.sql.Date sqlDate = new Date(date.getTime());
            preparedStatement.setDate(5, sqlDate);

            preparedStatement.setInt(6, 15000);

            preparedStatement.execute();
            System.out.println("--- INSERTED 1 ROW ---");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void updateExample() throws SQLException {
        String query = "UPDATE pers SET depart_id = ?, firstname = ?, lastname = ? WHERE pers_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Arthas");
        preparedStatement.setString(3, "Menethil");
        preparedStatement.setInt(4, 121);

        preparedStatement.execute();
        System.out.println("--- UPDATED 1 ROW ---");
    }

    public void deleteExample() throws SQLException {
        String query = "DELETE FROM pers WHERE pers_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 121);
        preparedStatement.execute();
        System.out.println("--- DELETED 1 ROW ---");
    }

    public void autoCommitExample() {
        try {
            System.out.println("--- SET AUTO-COMMIT to FALSE! ---");
            connection.setAutoCommit(false);
            insertExample();
            String query = "INSERT INTO pers (pers_id, depart_id, firstname, lastname, middlename, hire_date, salary)" +
                    " VALUES (125, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Jhon");
            preparedStatement.setString(3, "Snow");
            preparedStatement.setString(4, "Petrovich");

            String strDate = "13.08.06";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
            java.util.Date date = simpleDateFormat.parse(strDate);
            java.sql.Date sqlDate = new Date(date.getTime());
            preparedStatement.setDate(5, sqlDate);

            preparedStatement.setInt(6, 15000);

            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
