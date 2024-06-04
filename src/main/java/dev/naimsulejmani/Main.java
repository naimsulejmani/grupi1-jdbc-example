package dev.naimsulejmani;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. conneciton url / string
        String connectionString = "jdbc:sqlserver://127.0.0.1:1433;encrypt=false;username=sa;password=C@cttusEducation;databaseName=TSQL";
//        String url =              "jdbc:sqlserver://127.0.0.1;encrypt=false;username=sa;password=C@cttusEducation;";

        // 2. Krijo connection me databaze ne baze te connection string
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            System.out.println("Connected successfully!");
            // 3. Krijo nje deklarate dhe vendos kuery ne to
            Statement statement = connection.createStatement();

//            String insertQuery = """
//                    INSERT INTO dbo.Products
//                    VALUES ('Bravo Orange', 'Pije Freskuese', 0.85, '2025-01-01');
//                    """;
//
//            int rowsAffected = statement.executeUpdate(insertQuery);
//
//            System.out.printf("%d rows affected\n", rowsAffected);


//            String insertQuery = """
//                    INSERT INTO dbo.Products
//                    VALUES ('Uje Zajqeci', 'Pije Freskuese', 0.45, '2029-01-01');
//                    """;
//
//            boolean isQuery = statement.execute(insertQuery);
//
//            if (isQuery) {
//                System.out.println("Duhet me lexu ResultSet");
//                ResultSet resultSet = statement.getResultSet();
//            } else {
//                int rowsAffected = statement.getUpdateCount();
//                System.out.printf("%d rows affected\n", rowsAffected);
//            }


//            String selectQuery = """
//                    SELECT * FROM dbo.Products;
//                    """;
//
//            ResultSet resultSet = statement.executeQuery(selectQuery);
//            while (resultSet.next()) {
//                int id = resultSet.getInt("Id");
//                String name = resultSet.getString(2);
//                String description = resultSet.getObject(3, String.class);
//                double price = resultSet.getObject("Price", Double.class);
//                Date date = resultSet.getDate("BestBefore");
//                LocalDate bestBefore = date.toLocalDate();
//
//                System.out.printf("%5d %30s %50s %10.2f %15s %n", id, name, description, price, bestBefore);
//            }

            Scanner reader = new Scanner(System.in);
            System.out.println("Shkruaj nje emer: ");
            String name = reader.nextLine();

            String selectQuery = "SELECT * FROM dbo.Products WHERE Name = '" + name + "'";
            System.out.println(selectQuery);

            boolean isQuery = statement.execute(selectQuery);
            if (isQuery) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    name = resultSet.getString(2);
                    String description = resultSet.getObject(3, String.class);
                    double price = resultSet.getObject("Price", Double.class);
                    Date date = resultSet.getDate("BestBefore");
                    LocalDate bestBefore = date.toLocalDate();

                    System.out.printf("%5d %30s %50s %10.2f %15s %n", id, name, description, price, bestBefore);
                }
            } else {
                System.out.printf("%d rows affected! %n", statement.getUpdateCount());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
