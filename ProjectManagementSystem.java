import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import javax.xml.crypto.Data;

/**
 * This class represents a project management system, providing functionalities
 * for user authentication, project and task management.
 */
public class ProjectManagementSystem {

  private User user;
  private Project project;
  private ArrayList<Project> projects;
  private final UserList userList;
  private final ProjectList projectList;

  /**
   * Constructs a new instance of ProjectManagementSystem and initializes the user
   * list.
   */
  public ProjectManagementSystem() {
    userList = UserList.getInstance();
    projectList = ProjectList.getInstance();
  }

  /**
   * Attempts to log in a user with the provided username and password.
   *
   * @param username the username of the user
   * @param password the password of the user
   * @return true if login is successful, false otherwise
   */
  public boolean login(String username, String password) {
    User user = userList.getUser(username);
    if (user != null && user.getPassword().equals(password)) {
      this.user = user; // Set the user field
      System.out.println("Login successful!");
      return true;
    } else {
      System.out.println(
        "Login failed. Please check your username and password."
      );
      return false;
    }
  }

  /**
   * Logs out the current user and resets the state.
   */
  public void logout() {
    Database.saveProjects(projects);
    Database.saveUsers();
    this.user = null;
    this.project = null;
    System.out.println("User has been logged out.");
  }

  /**
   * Attempts to sign up a new user with the provided details.
   *
   * @param username  the username of the new user
   * @param password  the password of the new user
   * @param firstName the first name of the new user
   * @param lastName  the last name of the new user
   * @param email     the email of the new user
   * @return true if sign up is successful, false otherwise
   */
  public boolean signUp(
    String username,
    String password,
    String firstName,
    String lastName,
    String email
  ) {
    boolean isSignUpSuccessful = userList.addUser(
      username,
      password,
      firstName,
      lastName,
      email
    );
    if (isSignUpSuccessful) {
      boolean isSaveSuccessful = Database.saveUsers();
      if (!isSaveSuccessful) {
        // Handle database save failure
        System.out.println("Failed to save user data to the database.");
      }
    } else {
      // Handle signup failure
      System.out.println("User signup failed. Username may already exist.");
    }
    return isSignUpSuccessful;
  }

  /**
   * Creates a new project with the given name.
   *
   * @param name the name of the new project
   * @return true if the project is created successfully, false otherwise
   */
  public boolean createProject(String name) {
    projects = ProjectList.getInstance().addProject(name);
    return project != null;
  }

  /**
   * Adds a new task to the current project.
   *
   * @param taskName the name of the new task
   * @return true if the task is added successfully, false otherwise
   */
  public boolean addTaskToProject(String name) {
    if (project == null) {
        System.out.println("No project selected. Please choose a project or create one.");
        return false;
    }
    if (user == null) {
        System.out.println("No user logged in. Please log in first.");
        return false;
    }
  
    Task task = new Task(name);
    ProjectList.getInstance();
    project.addTask(name);  // Add the task to the project
    ArrayList<Project> projectList = ProjectList.getInstance().getAllProjects();
    boolean isSaveSuccessful = Database.saveProjects(projects); // Save the updated projects to the database
    if (!isSaveSuccessful) {
        System.out.println("Failed to save project data to the database.");
        return false;
    }
    return true;
}

  /**
   * Adds a comment to the current project.
   *
   * @param userInput the text of the comment
   * @return true if the comment is added successfully, false otherwise
   */
  public boolean addCommentToProject(String userInput) {
    if (project == null) {
      System.out.println(
        "No project selected. Please choose a project or create one."
      );
      return false;
    }
    if (user == null) {
      System.out.println("No user logged in. Please log in first.");
      return false;
    }

    Comment comment = new Comment(userInput, user);
    project.addComment(comment);
    ArrayList<Project> projectList = ProjectList.getInstance().getAllProjects();
    boolean isSaveSuccessful = Database.saveProjects(projects);
    if (!isSaveSuccessful) {
      System.out.println("Failed to save project data to the database.");
      return false;
    }
    return true;
  }

  /**
   * Lists all available projects and lets the user choose one.
   *
   * @param scanner The Scanner object for user input.
   */
  public void selectProject(Scanner scanner) {
    ArrayList<Project> projects = ProjectList.getInstance().getAllProjects();
    if (projects.isEmpty()) {
      System.out.println("No projects available.");
      return;
    }

    System.out.println("Available Projects:");
    for (int i = 0; i < projects.size(); i++) {
      System.out.println((i + 1) + ". " + projects.get(i).getName());
    }

    System.out.print("Choose a project (enter the number): ");
    if (scanner.hasNextInt()) {
      int choice = scanner.nextInt();
      if (choice > 0 && choice <= projects.size()) {
        project = projects.get(choice - 1);
        System.out.println("Project " + project.getName() + " selected.");
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    } else {
      System.out.println("Invalid input. Please enter a number.");
      scanner.next(); // clear the invalid input
    }
  }

//   public boolean createProject(String name) {
//     if (user == null) {
//         System.out.println("No user logged in. Please log in first.");
//         return false;
//     }

//     // Create a new project
//     Project project = new Project(UUID.randomUUID(), name);

//     // Create and add the base 5 columns
//     ArrayList<Column> baseColumns = project.createColumns();
//     project.setColumns(baseColumns);

//     // Set the project's scrum master and add the project to the user's projects
//     project.setScrumMaster(user);
//     // user.addProject(project);

//     // Save the updated projects to the database
//     ArrayList<Project> projectList = ProjectList.getInstance().getAllProjects();
//     boolean isSaveSuccessful = Database.saveProjects();

//     if (!isSaveSuccessful) {
//         System.out.println("Failed to save project data to the database.");
//         return false;
//     }

//     return true;
// }

  /**
   * Loads users from the database.
   */
  private void loadUsers() {
    ArrayList<User> users = Database.getUsers();
    for (User user : users) {
      userList.addUser(user);
    }
  }
}
