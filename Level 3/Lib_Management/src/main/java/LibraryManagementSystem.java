import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
    private Connection conn;
    private Scanner sc;

    public LibraryManagementSystem() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "");
            sc = new Scanner(System.in);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    //Book
    public void addBook() {
        try {
            System.out.print("Enter book title: ");
            String title = sc.nextLine();
            System.out.print("Enter author: ");
            String author = sc.nextLine();

            String sql = "INSERT INTO books(title, author, available) VALUES (?, ?, TRUE)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, author);
            pst.executeUpdate();
            System.out.println("✅ Book added!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void viewBooks() {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
            System.out.println("\n--- Books ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("title") +
                        " | " + rs.getString("author") +
                        " | Available: " + rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void deleteBook() {
        try {
            System.out.print("Enter Book ID to delete: ");
            int id = sc.nextInt(); sc.nextLine();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM books WHERE id=?");
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            System.out.println(rows > 0 ? "Book deleted!" : "Book not found.");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    // User
    public void addUser() {
        try {
            System.out.print("Enter user name: ");
            String name = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();

            String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.executeUpdate();
            System.out.println("User added!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void viewUsers() {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users");
            System.out.println("\n--- Users ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") +
                        " | " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void deleteUser() {
        try {
            System.out.print("Enter User ID to delete: ");
            int id = sc.nextInt(); sc.nextLine();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE id=?");
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            System.out.println(rows > 0 ? "User deleted!" : "User not found.");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    // TRANSACTIONS
    public void borrowBook() {
        try {
            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();
            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine();

            PreparedStatement check = conn.prepareStatement("SELECT available FROM books WHERE id=?");
            check.setInt(1, bookId);
            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getBoolean("available")) {

                PreparedStatement pst = conn.prepareStatement(
                        "INSERT INTO transactions(user_id, book_id) VALUES (?, ?)");
                pst.setInt(1, userId);
                pst.setInt(2, bookId);
                pst.executeUpdate();


                PreparedStatement update = conn.prepareStatement("UPDATE books SET available=FALSE WHERE id=?");
                update.setInt(1, bookId);
                update.executeUpdate();

                System.out.println("Book borrowed!");
            } else {
                System.out.println("Book not available.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void returnBook() {
        try {
            System.out.print("Enter Transaction ID: ");
            int transId = sc.nextInt();
            sc.nextLine();


            PreparedStatement pst = conn.prepareStatement("SELECT book_id FROM transactions WHERE id=? AND return_date IS NULL");
            pst.setInt(1, transId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");


                PreparedStatement update = conn.prepareStatement("UPDATE transactions SET return_date=NOW() WHERE id=?");
                update.setInt(1, transId);
                update.executeUpdate();


                PreparedStatement bookUpdate = conn.prepareStatement("UPDATE books SET available=TRUE WHERE id=?");
                bookUpdate.setInt(1, bookId);
                bookUpdate.executeUpdate();

                System.out.println("Book returned!");
            } else {
                System.out.println("Transaction not found or already returned.");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void viewTransactions() {
        try {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT t.id, u.name, b.title, t.borrow_date, t.return_date " +
                            "FROM transactions t " +
                            "JOIN users u ON t.user_id=u.id " +
                            "JOIN books b ON t.book_id=b.id");

            System.out.println("\n--- Transactions ---");
            while (rs.next()) {
                System.out.println("Transaction: " + rs.getInt("id") +
                        " | User: " + rs.getString("name") +
                        " | Book: " + rs.getString("title") +
                        " | Borrowed: " + rs.getTimestamp("borrow_date") +
                        " | Returned: " + rs.getTimestamp("return_date"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    // ================= MENU =================
    public void menu() {
        while (true) {
            System.out.println("\n=== Library Management ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Add User");
            System.out.println("5. View Users");
            System.out.println("6. Delete User");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Transactions");
            System.out.println("10. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> deleteBook();
                case 4 -> addUser();
                case 5 -> viewUsers();
                case 6 -> deleteUser();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> viewTransactions();
                case 10 -> {return;}
                default -> System.out.println("Invalid option!");
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem app = new LibraryManagementSystem();
        app.menu();
    }
}
