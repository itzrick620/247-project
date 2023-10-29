import java.util.Scanner;

public class ProjectManagementSystem {
    private User user;
    private Project project;
    private UserList userList;  

    public ProjectManagementSystem() {
        userList = UserList.getInstance();  
    }

    public boolean login(String username, String password) {
        User user = userList.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            // Successful login
            return true;
        } else {
            // Failed login
            System.out.println("Login failed. Please check your username and password.");
            return false;
        }
    }
    

    public boolean signUp(String username, String password, String firstName, String lastName, String email) {
        boolean isSignUpSuccessful = userList.addUser(username, password, firstName, lastName, email);
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
    
    

    public boolean createProject(String name) {
        project = ProjectList.getInstance().addProject(name);
        return project != null;
    }

    public boolean addTaskToProject(String name) {
        return project.addTask(name) != null;
    }

    public boolean makeProjectComment(String userInput) {
        return project.addTask(userInput) != null;
    }
}
