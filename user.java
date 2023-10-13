import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Project> projects;

    public User(String username, String password, String firstName, String lastName, String email, ArrayList<Project> projects) {}

    public User(UUID id, String username, String password, String firstName, String lastName, String email) {}
}