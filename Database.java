import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The Database class handles data storage and retrieval from JSON files.
 */
public class Database {
    private static final String USERS_JSON_FILENAME = "C:\\Users\\gbujo\\VSCode_GitHub\\247-project\\jsons\\user.json"; 
    private static final String PROJECTS_JSON_FILENAME = "C:\\Users\\gbujo\\VSCode_GitHub\\247-project\\jsons\\project.json"; 

    /**
     * Retrieves a list of users from the JSON file.
     *
     * @return An ArrayList containing the retrieved users
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (FileReader fileReader = new FileReader(USERS_JSON_FILENAME)) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            for (Object obj : jsonArray) {
                JSONObject userJson = (JSONObject) obj;
                String uuid = (String) userJson.get("UUID");
                String username = (String) userJson.get("username");
                String password = (String) userJson.get("password");
                String firstName = (String) userJson.get("firstName");
                String lastName = (String) userJson.get("lastName");
                String email = (String) userJson.get("email");

                User user = new User(UUID.fromString(uuid), username, password, firstName, lastName, email);
                users.add(user);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Saves the list of users to a JSON file.
     *
     * @return true if the users are saved successfully, false otherwise
     */
    public static boolean saveUsers() {
        ArrayList<User> userList = UserList.getInstance().getUsers();
        JSONArray jsonArray = new JSONArray();

        for (User user : userList) {
            JSONObject userJson = new JSONObject();
            userJson.put("UUID", user.getId().toString());
            userJson.put("username", user.getUsername());
            userJson.put("password", user.getPassword());
            userJson.put("firstName", user.getFirstName());
            userJson.put("lastName", user.getLastName());
            userJson.put("email", user.getEmail());
            jsonArray.add(userJson);
        }

        try (FileWriter fileWriter = new FileWriter(USERS_JSON_FILENAME)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Saves the list of projects to a JSON file.
     *
     * @return true if the projects are saved successfully, false otherwise
     */
    public static boolean saveProjects() {
        ArrayList<Project> projectList = ProjectList.getInstance().getAllProjects();
        JSONArray jsonArray = new JSONArray();

        for (Project project : projectList) {
            JSONObject projectJson = new JSONObject();
            projectJson.put("projectId", project.getId().toString());
            projectJson.put("projectName", project.getName());
            // Add more attributes as needed

            jsonArray.add(projectJson);
        }

        try (FileWriter fileWriter = new FileWriter(PROJECTS_JSON_FILENAME)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a list of projects from the JSON file.
     *
     * @return An ArrayList containing the retrieved projects
     */
    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        try (FileReader fileReader = new FileReader(PROJECTS_JSON_FILENAME)) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            for (Object obj : jsonArray) {
                JSONObject projectJson = (JSONObject) obj;
                String projectId = (String) projectJson.get("projectId");
                String projectName = (String) projectJson.get("projectName");
                // Add more attributes as needed

                Project project = new Project(UUID.fromString(projectId), projectName);
                // Add more attributes as needed

                projects.add(project);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
