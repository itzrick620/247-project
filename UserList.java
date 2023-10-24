import java.util.ArrayList;

/**
 * Manages a list of users in the project management system.
 */
public class UserList {
    private ArrayList<User> users;  // List of users
    private static UserList userListInstance;  // Singleton instance

    private UserList() {
        users = Database.getUsers();
    }

    /**
     * Gets the singleton instance of UserList.
     *
     * @return The UserList instance
     */
    public static UserList getInstance() {
        if (userListInstance == null) {
            userListInstance = new UserList();
        }
        return userListInstance;
    }

    /**
     * Adds a new user to the list.
     *
     * @param username  User's username
     * @param password  User's password
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param email     User's email address
     * @return true if the user is added successfully, false otherwise
     */
    public boolean addUser(String username, String password, String firstName, String lastName, String email) {
        User newUser = new User(username, password, firstName, lastName, email);
        return users.add(newUser);
    }

    /**
     * Removes a user from the list.
     *
     * @param username The username of the user to remove
     * @return true if the user is removed successfully, false otherwise
     */
    public boolean removeUser(String username) {
        User userToRemove = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToRemove = user;
                break;
            }
        }
        return userToRemove != null && users.remove(userToRemove);
    }

    /**
     * Gets a user by their username.
     *
     * @param username The username of the user to retrieve
     * @return The User object if found, null otherwise
     */
    public boolean getUser(String username) {
        User userToAdd = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToAdd = user;
                break;
            }
        }
        return userToAdd != null && users.add(userToAdd);
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public void saveUsers() {
        Database.saveUsers();
    }
}
