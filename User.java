import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a user in the project management system.
 */
public class User {
    private UUID id;            // Unique identifier for the user
    private String username;    // User's username
    private String password;    // User's password
    private String firstName;   // User's first name
    private String lastName;    // User's last name
    private String email;       // User's email address
    private ArrayList<Project> projects;  // Projects associated with the user

    /**
     * Creates a new User instance.
     *
     * @param username  User's username
     * @param password  User's password
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param email     User's email address
     */
    public User(String username, String password, String firstName, String lastName, String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projects = new ArrayList<>();
    }

    /**
     * Creates a new User instance with a specified UUID.
     *
     * @param id        User's UUID
     * @param username  User's username
     * @param password  User's password
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param email     User's email address
     */
    public User(UUID id, String username, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projects = new ArrayList<>();
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the id of the user.
     *
     * @return The id of the user
     */
    public UUID getId() {
        return id;
    }

    public String toString(){
        return this.firstName;
    }
}
