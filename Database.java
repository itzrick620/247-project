import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The Database class handles data storage and retrieval from JSON files.
 */
public class Database {

  private static final String USERS_JSON_FILENAME ="jsons\\user.json";
  private static final String PROJECTS_JSON_FILENAME ="jsons\\project.json";
  private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

  private static ArrayList<User> allUsers; // Assuming this is loaded once and then queried in memory

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

        User user = new User(
          UUID.fromString(uuid),
          username,
          password,
          firstName,
          lastName,
          email
        );
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
      System.out.println("Error saving projects: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }
  static {
    allUsers = getUsers(); // Load users once during initialization
}

    /**
     * Retrieves a list of projects from a JSON file.
     */
    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        JSONParser parser = new JSONParser();
    
        try (FileReader fileReader = new FileReader(PROJECTS_JSON_FILENAME)) {
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
    
            for (Object obj : jsonArray) {
                JSONObject projectJson = (JSONObject) obj;
                Project project = parseProject(projectJson);
                
                projects.add(project);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving the projects. Please try again later.");
        }
    
        return projects;
    }

    /**
     * Saves a list of projects to a JSON file.
     */
    public static boolean saveProjects() {
        ArrayList<Project> projects = ProjectList.getInstance().getAllProjects();
        System.out.println("Saving projects to JSON...");
        try {
            JSONArray projectArray = new JSONArray();
    
            for (Project project : projects) {
                JSONObject projectJson = projectToJson(project);
                projectArray.add(projectJson);
            }
    
            try (FileWriter fileWriter = new FileWriter(PROJECTS_JSON_FILENAME)) {
                fileWriter.write(projectArray.toJSONString());
                System.out.println("Projects saved successfully.");
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error saving projects: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static Project parseProject(JSONObject projectJson) throws ParseException {
        String projectId = (String) projectJson.get("projectId");
        String projectName = (String) projectJson.get("projectName");
        String footnotes = (String) projectJson.get("footnotes");
    
        Project project = new Project(UUID.fromString(projectId), projectName);
        project.setFootnotes(footnotes);
    
        JSONArray commentsArray = (JSONArray) projectJson.get("comments");
        for (Object commentObj : commentsArray) {
            JSONObject commentJson = (JSONObject) commentObj;
            Comment comment = parseComment(commentJson);
            project.addComment(comment);
        }
    
        JSONArray developersArray = (JSONArray) projectJson.get("developers");
        for (Object developerObj : developersArray) {
            String developerId = (String) developerObj;
            User developer = getUserById(developerId);
            if (developer != null) {
                project.addDeveloper(developer);
            }
        }
    
        String scrumMasterId = (String) projectJson.get("scrumMaster");
        User scrumMaster = getUserById(scrumMasterId);
        if (scrumMaster != null) {
            project.setScrumMaster(scrumMaster);
        }
    
        JSONArray columnsArray = (JSONArray) projectJson.get("columns");
        for (Object columnObj : columnsArray) {
            JSONObject columnJson = (JSONObject) columnObj;
            Column column = parseColumn(columnJson, project);
            project.addColumn(column);
        }
    
        return project;
    }

    private static Comment parseComment(JSONObject commentJson) {
        String userInput = (String) commentJson.get("userInput");
        String userId = (String) commentJson.get("user");
        String dateString = (String) commentJson.get("date");

        User user = getUserById(userId);
        Date date = parseDate(dateString);

        return new Comment(userInput, user, date, new ArrayList<>());
    }

    private static User getUserById(String userId) {
        for (User user : allUsers) {
            if (user.getId().toString().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (java.text.ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateString, e);
        }
    }

    private static Column parseColumn(JSONObject columnJson, Project project) {
        String title = (String) columnJson.get("title");
        Column column = new Column(title);
    
        JSONArray tasksArray = (JSONArray) columnJson.get("tasks");
        for (Object taskObj : tasksArray) {
            String taskId = (String) taskObj; // Task ID is a string in this case
            Task task = project.getTaskById(UUID.fromString(taskId));
            if (task != null) {
                column.addTask(task);
            }
        }
    
        return column;
    }

    private static JSONObject projectToJson(Project project) {
        JSONObject projectJson = new JSONObject();
        
        projectJson.put("projectId", project.getId().toString());
        projectJson.put("projectName", project.getName());
        projectJson.put("footnotes", project.getFootnotes());
        
        JSONArray commentsArray = new JSONArray();
        for (Comment comment : project.getComments()) {
            JSONObject commentJson = new JSONObject();
            commentJson.put("userInput", comment.getUserInput());
            commentJson.put("user", comment.getUser().getId().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            commentJson.put("date", dateFormat.format(comment.getDate()));
            commentJson.put("replies", new JSONArray());  // Assuming replies are empty for now
            commentsArray.add(commentJson);
        }
        projectJson.put("comments", commentsArray);
        
        JSONArray developersArray = new JSONArray();
        for (User developer : project.getDevelopers()) {
            developersArray.add(developer.getId().toString());
        }
        projectJson.put("developers", developersArray);
        
        if (project.getScrumMaster() != null) {
            projectJson.put("scrumMaster", project.getScrumMaster().getId().toString());
        }
        
        JSONArray columnsArray = new JSONArray();
        for (Column column : project.getColumns()) {
            JSONObject columnJson = new JSONObject();
            columnJson.put("title", column.getTitle());
        
            JSONArray tasksArray = new JSONArray();
            for (Task task : column.getTasks()) {
                tasksArray.add(task.getId().toString());
            }
            columnJson.put("tasks", tasksArray);
        
            columnsArray.add(columnJson);
        }
        projectJson.put("columns", columnsArray);
        
        return projectJson;
    }
    
}