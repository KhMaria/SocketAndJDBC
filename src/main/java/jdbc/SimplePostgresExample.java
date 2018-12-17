package jdbc;

import java.sql.*;
import java.util.UUID;

public class SimplePostgresExample {

    private Connection connection;

    public SimplePostgresExample(Connection connection) {
        this.connection = connection;
    }

    public void selectExample() throws SQLException {
        String query = "SELECT * FROM users";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            String userId = resultSet.getString("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Integer age = resultSet.getInt("age");
            System.out.println(String.format("User [%s, %s %s, %d]", userId, firstName, lastName, age));
        }
        System.out.println("--- ALL ROWS ARE FETCHED ---");
    }

    public void insertExample() throws SQLException {
        String query = "INSERT INTO users (user_id, first_name, last_name, age) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, UUID.randomUUID());
        preparedStatement.setString(2, "Jhon");
        preparedStatement.setString(3, "Snow");
        preparedStatement.setInt(4, 100);

        preparedStatement.execute();
        System.out.println("--- INSERTED 1 ROW ---");
    }

    public void updateExample() throws SQLException {
        String query = "UPDATE users SET first_name = ?, last_name = ?, age = ? WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "Arthas");
        preparedStatement.setString(2, "Menethil");
        preparedStatement.setInt(3, 200);
        preparedStatement.setObject(4, UUID.fromString("6f47de63-d61a-472b-ba8b-bf4365d46122"));

        preparedStatement.execute();
        System.out.println("--- UPDATED 1 ROW ---");
    }

    public void deleteExample() throws SQLException {
        String query = "DELETE FROM users";
        Statement statement = connection.createStatement();
        statement.execute(query);
        System.out.println("--- DELETED ALL ROWS ---");
    }

    public void autoCommitExample() {
        try {
            System.out.println("--- SET AUTO-COMMIT to FALSE! ---");
            connection.setAutoCommit(false);
            insertExample();

            String query = "INSERT INTO users (first_name123, last_name123, age123) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Jhon123");
            preparedStatement.setString(2, "Snow123");
            preparedStatement.setInt(3, 100);

            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
