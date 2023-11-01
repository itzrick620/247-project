import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a project in a project management system.
 */
public class Project {

    private final UUID id;
    private final String name;
    private String footnotes;
    private User owner;
    private User scrumMaster;
    private ArrayList<Comment> comments;
    private ArrayList<User> developers;
    private final ArrayList<Task> tasks;
    private ArrayList<Column> columns;

    /**
     * Constructs a new project with a generated UUID and the given name.
     *
     * @param name the name of the project
     */
    public Project(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.comments = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    /**
     * Constructs a new project with the specified UUID and name.
     *
     * @param id   the UUID of the project
     * @param name the name of the project
     */
    public Project(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.comments = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    /**
     * Adds a task to the project.
     *
     * @param task the name of the task to be added
     * @return the added task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // /**
    //  * Removes a task from the project.
    //  *
    //  * @param task the task to be removed
    //  */
    // public void removeTask(Task task) {
    //     tasks.remove(task);
    // }

    /**
     * Shares the project.
     */
    public void share() {
        System.out.println("Sharing project: " + name);
    }

    /**
     * Adds footnotes to the project.
     *
     * @param footnotes the footnotes to be added
     */
    public void addFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }

    /**
     * Gets the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the project.
     *
     * @return the ID of the project
     */
    public UUID getId() {
        return id;
    }

    // /**
    //  * Gets the tasks associated with this project.
    //  *
    //  * @return a list of tasks
    //  */
    // public ArrayList<Task> getTasks() {
    //     return new ArrayList<>(tasks);
    // }

    /**
     * Adds a comment to this project.
     *
     * @param comment the comment to be added
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    /**
     * Gets the comments associated with this project.
     *
     * @return a list of comments
     */
    public ArrayList<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public static ArrayList<Column> createColumns() {
        ArrayList<Column> columns = new ArrayList<>();

        columns.add(new Column("Added Tasks"));
        columns.add(new Column("Abandoned"));
        columns.add(new Column("Doing"));
        columns.add(new Column("ToDo"));
        columns.add(new Column("Done"));

        return columns;
    }
    

    /**
     * Gets the columns associated with this project.
     * @param column
     *
     * @return an array of columns
     */
    public ArrayList<Column> getColumns() {
        return new ArrayList<>(columns);
    }

    /**
     * Gets the scrum master of this project.
     *
     * @return the scrum master
     */
    public User getScrumMaster() {
        return scrumMaster;
    }

    /**
     * Gets the developers associated with this project.
     *
     * @return an array of developers
     */
    public ArrayList<User> getDevelopers() {
        return new ArrayList<>(developers);
    }

    /**
     * Gets the user associated with this project.
     *
     * @return the user
     */
    public User getUser() {
        return owner;
    }

    /**
     * Gets the footnotes of this project.
     *
     * @return the footnotes
     */
    public String getFootnotes() {
        return footnotes;
    }

    /**
     * Sets the footnotes of this project.
     *
     * @param footnotes the footnotes to be set
     */
    public void setFootnotes(String footnotes) {
        this.footnotes = footnotes;
    }

    /**
     * Adds a developer to this project.
     *
     * @param developer the developer to be added
     */
    public void addDeveloper(User developer) {
        developers.add(developer);
    }

    /**
     * Sets the scrum master of this project.
     *
     * @param scrumMaster the scrum master to be set
     */
    public void setScrumMaster(User scrumMaster) {
        this.scrumMaster = scrumMaster;
    }

    

    /**
     * Retrieves a task by its ID.
     *
     * @param taskId the ID of the task
     * @return the task if found, null otherwise
     */
    public Task getTaskById(UUID taskId) {
        for (Column column : columns) {
            for (Task task : column.getTasks()) {
                if (task.getId().equals(taskId)) {
                    return task;
                }
            }
        }
        return null;
    }

    /**
     * Removes a developer from this project.
     *
     * @param userId the UUID of the developer to be removed
     * @return true if the developer was removed, false otherwise
     */
    public boolean removeDeveloper(UUID userId) {
        return developers.removeIf(user -> user.getId().equals(userId));
    }

    /**
     * Removes a comment from this project.
     *
     * @param commentId the UUID of the comment to be removed
     * @return true if the comment was removed, false otherwise
     */
    public boolean removeComment(UUID commentId) {
        return comments.removeIf(comment -> comment.getId().equals(commentId));
    }

    /**
     * Sets the owner of this project.
     *
     * @param owner the user to be set as the owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the owner of this project.
     *
     * @return the owner of the project
     */
    public User getOwner() {
        return owner;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public boolean moveTaskToColumn(UUID taskId, Column sourceColumn, Column targetColumn) {
        Task taskToMove = sourceColumn.getTaskById(taskId);
        if (taskToMove != null) {
            targetColumn.addTask(taskToMove);
            sourceColumn.removeTask(taskId);
            return true;
        }
        return false;
    }

    /**
     * Adds a column to this project.
     *
     * @param column the column to be added
     */
    public void addColumn(Column column) {
        this.columns.add(column);
    }

    public void setDevelopers(ArrayList<User> developers) {
        this.developers = developers;
    }
    
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}

