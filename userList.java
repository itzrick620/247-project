import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;
    private static UserList userList;

    private UserList() {}

    public static UserList getInstance() {
        return userList;
    }

    public User addUser(String username, String password, String firstName, String lastName, String email) {
        return null;
    }

    public User removeUser(String username) {
        return null;
    }

    public User getUser(String username) {
        return null;
    }
}
