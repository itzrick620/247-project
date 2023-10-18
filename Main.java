import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create instances of necessary classes
        UserList userList = UserList.getInstance();
        ProjectManagementSystem pms = new ProjectManagementSystem();

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("Welcome to the Project Management System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume the newline character
                    pms.login();
                    break;
                case 2:
                    scanner.nextLine(); // Consume the newline character
                    pms.signUp();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close(); // Close the scanner before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
