import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        Scanner sc = new Scanner(System.in);

        try {
            Statement stmt = conn.createStatement();

            // Table create cheyadam
            String sql = "CREATE TABLE IF NOT EXISTS books (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "title VARCHAR(100)," +
                         "author VARCHAR(100)" +
                         ")";
            stmt.execute(sql);

            int choice;
            do {
                System.out.println("\n--- Library Menu ---");
                System.out.println("1. Add Book");
                System.out.println("2. Update Book");
                System.out.println("3. Delete Book");
                System.out.println("4. View All Books");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: // Add Book
                        System.out.print("Enter book title: ");
                        String newTitle = sc.nextLine();
                        System.out.print("Enter author name: ");
                        String newAuthor = sc.nextLine();
                        stmt.execute("INSERT INTO books (title, author) VALUES ('" + newTitle + "', '" + newAuthor + "')");
                        System.out.println("Book added: " + newTitle + " by " + newAuthor);
                        break;

                    case 2: // Update Book
                        System.out.print("Enter book ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new title: ");
                        String updatedTitle = sc.nextLine();
                        System.out.print("Enter new author: ");
                        String updatedAuthor = sc.nextLine();
                        stmt.execute("UPDATE books SET title='" + updatedTitle + "', author='" + updatedAuthor + "' WHERE id=" + updateId);
                        System.out.println("Book updated: ID " + updateId);
                        break;

                    case 3: // Delete Book
                        System.out.print("Enter book ID to delete: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();
                        stmt.execute("DELETE FROM books WHERE id=" + deleteId);
                        System.out.println("Book deleted: ID " + deleteId);
                        break;

                    case 4: // View All Books
                        ResultSet rs = stmt.executeQuery("SELECT * FROM books");
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + " | " +
                                               rs.getString("title") + " | " +
                                               rs.getString("author"));
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
