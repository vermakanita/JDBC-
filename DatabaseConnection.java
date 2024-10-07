package src;
import java.sql.*;
import java.util.*;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.util.Scanner;

/**
 * DatabaseConnection
 */
 class DatabaseConnection {

    static String url = "jdbc:mysql://localhost:3306/jdbstest1";
    static String user = "root";
    static String password = "1234";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
    
}

class Test{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // System.out.println("enter Emp_id");
        // int Emp_id=sc.nextInt();
        System.out.println("enter name");
        String name=sc.next();
        System.out.println("enter department");
        String department=sc.next();
        System.out.println("enter salary");
         Double salary=sc.nextDouble();
             

        try (Connection cn = DatabaseConnection.getConnection()) {
       
            String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
             PreparedStatement ps = cn.prepareStatement(sql);
            System.out.println("hello");
            // Set parameters for the placeholders
           // ps.setInt(1,Emp_id);        // Assuming id is an integer
            ps.setString(1, name);  // Name as a string
            ps.setString(2, department);
            ps.setDouble(3, salary);

            int rowInserted = ps.executeUpdate();
            if(rowInserted>0){
                System.out.println("inserted successfully");
            }
        } catch (SQLException e) {e.printStackTrace();}}}
 