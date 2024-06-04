package dev.naimsulejmani;

import java.sql.*;
import java.time.LocalDate;

public class PrepareStatement {
    public static void main(String[] args) {
        String connectionString = "jdbc:sqlserver://127.0.0.1:1433;encrypt=false;username=sa;password=C@cttusEducation;databaseName=TSQL";

        try {
            String insertQuery = """
                    INSERT INTO dbo.Products VALUES (?, ?, ?, ?);
                    """;
            String selectQuery = """
                    SELECT * FROM dbo.Products
                    WHERE Name = ?;
                    """;


            Connection connection = DriverManager.getConnection(connectionString);
//            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//            preparedStatement.setString(1, "Laptop X2");
//            preparedStatement.setString(2, "Very good laptop");
//            preparedStatement.setDouble(3, 999.99);
//            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
//
//            int rowAffected = preparedStatement.executeUpdate();
//            System.out.printf("%d rows affected\n", rowAffected);

            PreparedStatement prepareStatement = connection.prepareStatement(selectQuery);
            prepareStatement.setString(1,"Laptop X1';DELETE FROM dbo.Products;--");

            ResultSet resultSet = prepareStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString(2);
                String description = resultSet.getObject(3, String.class);
                double price = resultSet.getObject("Price", Double.class);
                Date date = resultSet.getDate("BestBefore");
                LocalDate bestBefore = date.toLocalDate();

                System.out.printf("%5d %30s %50s %10.2f %15s %n", id, name, description, price, bestBefore);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
