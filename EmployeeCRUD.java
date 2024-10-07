package src;
import java.sql.*;

public class EmployeeCRUD {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbstest1"; // Replace with your database URL
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a statement to execute SQL queries
            Statement statement = connection.createStatement();

            // Add a new employee
            addEmployee(statement, "John Doe", "Sales", 50000.00);

            // Retrieve all employees
            getAllEmployees(statement);

            // Update an employee's salary
            updateEmployeeSalary(statement, 1, 60000.00);

            // Delete an employee
            deleteEmployee(statement, 2);

            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Statement statement, String name, String department, double salary) throws SQLException {
        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, department);
        preparedStatement.setDouble(3, salary);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row inserted.");
        preparedStatement.close();
    }

    private static void getAllEmployees(Statement statement) throws SQLException {
        String sql = "SELECT * FROM employee";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");
            double salary = resultSet.getDouble("salary");
            System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary);
        }
        resultSet.close();
    }

    private static void updateEmployeeSalary(Statement statement, int id, double newSalary) throws SQLException {
        String sql = "UPDATE employee SET salary = ? WHERE id = ?";
        PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
        preparedStatement.setDouble(1, newSalary);
        preparedStatement.setInt(2, id);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row updated.");
        preparedStatement.close();
    }

    private static void deleteEmployee(Statement statement, int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row deleted.");
        preparedStatement.close();
    }
}