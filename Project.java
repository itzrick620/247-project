import java.util.UUID;
import java.util.ArrayList;

/**
 * The Project class represents a project in a project management system.
 */
public class Project {
    private UUID id; //task uuid
    private String name; //task name
    private String footnotes; //task footnotes
    private User owner; //task owner
    private User scrumMaster; //task scrummaster
    private ArrayList<Comment> comments; //comments associated with the project
    private ArrayList<User> developers; //developers associated with the project
    private ArrayList<Task> tasks;

    /**
     * Constructs a new project with a generated UUID and the given name.
     *
     * @param name The name of the project.
     */
    public Project(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.comments = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new project with the specified UUID and name.
     *
     * @param id   The UUID of the project.
     * @param name The name of the project.
     */
    public Project(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.comments = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the project.
     *
     * @param name The name of the task to be added.
     * @return The created Task object.
     */
    public Task addTask(String name) {
        Task task = new Task(name);
        tasks.add(task);
        return task;
    }

    /**
     * Removes a task from the project.
     *
     * @param task The task to be removed from the project.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Shares the project.
     */
    public void share() {
        // Implement the sharing functionality
    }

    /**
     * Adds footnotes to the project.
     *
     * @param footnotes The footnotes to be added to the project.
     */
    public void addFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }

      /**
     * Gets the id of the user.
     *
     * @return The id of the project
     */
    public UUID getId() {
        return id;
    }

    /**
     * 
     * 
     * @param name Getting the name of the project
    */
    public String getName() {
        return name;
    }
}
