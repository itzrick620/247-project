import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Database {
    private static final String USERS_JSON_FILENAME = "user.json"; // JSON file name for users

    public ArrayList<User> getUsers() {
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

    public boolean saveUsers(ArrayList<User> userList) {
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
    


    public boolean saveProjects() {
        return false;
    }

    public ArrayList<Project> getProjects() {
        return null;
    }
}
