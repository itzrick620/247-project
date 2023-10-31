import java.util.Scanner;

public class Main {

  private final ProjectManagementSystem pms;
  private final Scanner scanner;
  private boolean userLoggedIn;

  public Main() {
    this.pms = new ProjectManagementSystem();
    this.scanner = new Scanner(System.in);
    this.userLoggedIn = false;
  }

  public void start() {
    while (true) {
      System.out.println("Welcome to the Project Management System");
      if (userLoggedIn) {
        loggedInMenu();
      } else {
        loggedOutMenu();
      }
    }
  }

  private void loggedInMenu() {
    System.out.println("Logged in!");
    System.out.println("1. Create Project");
    System.out.println("2. Add Task to Project");
    System.out.println("3. Add Comment to Project");
    System.out.println("4. Select Project"); // Moved Select Project to option 4
    System.out.println("5. Logout"); // Moved Logout to option 5
    System.out.println("6. Exit");
    System.out.print("Choose an option: ");
  
    int choice = scanner.nextInt();
    scanner.nextLine(); 
  
    switch (choice) {
      case 1:
        createProject();
        break;
      case 2:
        addTaskToProject();
        break;
      case 3:
        addCommentToProject();
        break;
      case 4: 
        selectProject();
        break;
      case 5: 
        logout();
        break;
      case 6:
        exit();
        break;
      default:
        System.out.println("Invalid option. Please try again.");
    }
  }

  private void loggedOutMenu() {
    System.out.println("1. Login");
    System.out.println("2. Sign Up");
    System.out.println("3. Exit");
    System.out.print("Choose an option: ");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    switch (choice) {
      case 1:
        login();
        break;
      case 2:
        signUp();
        break;
      case 3:
        exit();
        break;
      default:
        System.out.println("Invalid option. Please try again.");
    }
  }

  private void createProject() {
    System.out.print("Enter project name: ");
    String projectName = scanner.nextLine();
    if (pms.createProject(projectName)) {
      System.out.println("Project created successfully.");
    } else {
      System.out.println("Failed to create the project.");
    }
  }

  private void addTaskToProject() {
    System.out.print("Enter task name: ");
    String taskName = scanner.nextLine();
    if (pms.addTaskToProject(taskName)) {
      System.out.println("Task added to the project successfully.");
    } else {
      System.out.println("Failed to add the task to the project.");
    }
  }

  private void addCommentToProject() {
    System.out.print("Enter your comment: ");
    String commentText = scanner.nextLine();
    if (pms.addCommentToProject(commentText)) {
      System.out.println("Comment added to the project successfully.");
    } else {
      System.out.println("Failed to add the comment to the project.");
    }
  }

  private void logout() {
    userLoggedIn = false;
    pms.logout();
    System.out.println("Logged out.");
  }

  private void selectProject() {
    pms.selectProject(scanner);
  }

  private void login() {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (pms.login(username, password)) {
      userLoggedIn = true;
      System.out.println("Login successful!");
    } else {
      System.out.println("Login failed. Please try again.");
    }
  }

  private void signUp() {
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

    if (pms.signUp(username, password, firstName, lastName, email)) {
      System.out.println("Signup successful. Welcome, " + firstName + "!");
    } else {
      System.out.println("Signup failed. Username already exists.");
    }
  }

  private void exit() {
    System.out.println("Exiting the program. Goodbye!");
    scanner.close();
    System.exit(0);
  }

  public static void main(String[] args) {
    new Main().start();
  }
}
