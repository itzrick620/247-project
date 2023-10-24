import java.util.Scanner;

public class ProjectManagementSystem {
    private User user;
    private Project project;
    private UserList userList;  // Add the UserList instance

    public ProjectManagementSystem() {
        userList = UserList.getInstance();  // Initialize the UserList instance
    }

    public boolean login(String username) {
        user = UserList.getInstance().getUser(username);
        return user != null;
    }

    public boolean signUp(String username, String password, String firstName, String lastName, String email) {
        return UserList.getInstance().addUser(username, password, firstName, lastName, email);
  }

    public boolean createProject(String name) {
        project = ProjectList.getInstance().addProject(name);
        return project != null;
    }

    public boolean addTaskToProject(Task task) {
        return project.addTask(task);
    }

    public void makeProjectComment(String userInput) {
        
    }

    //login
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Enter username: ");
        // String username = scanner.nextLine();
        // System.out.print("Enter password: ");
        // String password = scanner.nextLine();

        // // Check if the user exists and validate password
        // User loggedInUser = userList.getUser(username);
        // if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
        //     System.out.println("Login successful.");
        //     user = loggedInUser;  // Set the logged-in user
        // } else {
        //     System.out.println("Login failed. Please try again.");
        // }

    //signup
        //   Scanner scanner = new Scanner(System.in);
        
        //   System.out.print("Enter username: ");
        //   String username = scanner.nextLine();
        
        //   System.out.print("Enter password: ");
        //   String password = scanner.nextLine();
        
        //   System.out.print("Enter first name: ");
        //   String firstName = scanner.nextLine();
        
        //   System.out.print("Enter last name: ");
        //   String lastName = scanner.nextLine();
        
        //   System.out.print("Enter email: ");
        //   String email = scanner.nextLine();
        
        //   // Validate input data and add the user to the UserList
        //   if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
        //       System.out.println("Signup failed. Please provide all required information.");
        //       return;
        //   }
    
        //   if (userList.addUser(username, password, firstName, lastName, email)) {
        //       System.out.println("Signup successful.");
        //   } else {
        //       System.out.println("Signup failed. Username already exists.");
        //   }

        //   scanner.close();
}