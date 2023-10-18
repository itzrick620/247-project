import java.util.Scanner;

public class ProjectManagementSystem {
    private User user;
    private Project project;
    private UserList userList;  // Add the UserList instance

    public ProjectManagementSystem() {
        userList = UserList.getInstance();  // Initialize the UserList instance
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if the user exists and validate password
        User loggedInUser = userList.getUser(username);
        if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
            System.out.println("Login successful.");
            user = loggedInUser;  // Set the logged-in user
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void signUp() {
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      
      System.out.print("Enter password: ");
      String password = scanner.nextLine();
      
      System.out.print("Enter first name: ");
      String firstName = scanner.nextLine();
      
      System.out.print("Enter last name: ");
      String lastName = scanner.nextLine();
      
      System.out.print("Enter email: ");
      String email = scanner.nextLine();
      
      // Validate input data and add the user to the UserList
      if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
          System.out.println("Signup failed. Please provide all required information.");
          return;
      }
  
      if (userList.addUser(username, password, firstName, lastName, email)) {
          System.out.println("Signup successful.");
      } else {
          System.out.println("Signup failed. Username already exists.");
      }

      scanner.close();
  }

    public void createProject(String name) {
    }

    public boolean addTaskToProject(Task task) {
        return false;
    }

    public void makeProjectComment(String userInput) {
        
    }
}