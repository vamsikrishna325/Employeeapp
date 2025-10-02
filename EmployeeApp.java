import java.sql.*;
import java.util.*;

public class EmployeeApp {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";       
    private static final String PASSWORD = "Vamsikrishna@4238";   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connected to Database!");

            while (true) {
                System.out.println("\n--- Employee Database Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Name: ");
        sc.nextLine(); 
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("✅ Employee added!");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Employee Records ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("department") + " | " +
                        rs.getDouble("salary")
                );
            }
        }
    }

    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employee SET department=?, salary=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dept);
            ps.setDouble(2, salary);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Employee updated!" : "❌ Employee not found!");
        }
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to Delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employee WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Employee deleted!" : "❌ Employee not found!");
        }
    }
}

