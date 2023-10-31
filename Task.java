import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Represents a task in the project management system.
 */
public class Task {

  private UUID id; // Task UUID
  private String name;
  private User assignedUser;
  private ArrayList<Comment> comments;
  private ArrayList<String> history = new ArrayList<>();
  private Integer priority;
  private Date timeWorkedOn;
  private double hours;
  private boolean status;

  /**
   * Constructs a task with the given name.
   *
   * @param name The name of the task.
   */
  public Task(String name) {
    this.id = UUID.randomUUID(); // Generate a random UUID for the task
    this.name = name;
    this.assignedUser = null; // Initialize assignedUser to null
    this.comments = new ArrayList<>();
    this.priority = 0; // Default priority
    this.timeWorkedOn = new Date();
    this.hours = 0.0; // Initialize hours worked to 0
    this.status = false; // Task starts as not completed
  }

  /**
   * Tracks the time worked on the task.
   *
   * @param hoursWorked The number of hours worked on the task.
   */
  public void trackTime(double hoursWorked) {
    if (status) {
      // Add the hours worked to the existing total
      hours += hoursWorked;
      timeWorkedOn = new Date(); // Update the last time worked on
    } else {
      System.out.println(
        "Task is not in progress. Start the task before tracking time."
      );
    }
  }

  /**
   * Sets the priority of the task.
   *
   * @param priority The priority value to set.
   */
  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  /**
   * Tracks changes made to the task's attributes.
   *
   * @param attribute The name of the attribute being changed.
   * @param newValue  The new value of the attribute.
   */
  public void trackChanges(String attribute, String newValue) {
    if (status) {
      String changeLog =
        "Attribute '" +
        attribute +
        "' changed to '" +
        newValue +
        "' at " +
        new Date();
      System.out.println(changeLog);
    } else {
      System.out.println(
        "Task is not in progress. Start the task before tracking changes."
      );
    }
  }

  /**
   * Saves a historical event related to the task.
   *
   * @param event The event description to save.
   */
  public void saveHistory(String event) {
    history.add(event);
  }

  /**
   * Prints the task's historical events.
   */
  public void printHistory() {
    for (String event : history) {
      System.out.println(event);
    }
  }

  /**
   * Performs auto-saving of task data.
   * You can implement auto-saving logic here, e.g., periodic saving of task data.
   */
  public void autoSave() {}

  /**
   * Marks the task as completed and sets the completion time.
   */
  public void markAsCompleted() {
    status = true;
    timeWorkedOn = new Date(); // Set the completion time
  }

  /**
   * Gets the name of the task.
   *
   * @return The name of the task.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the ID of the task.
   *
   * @return The ID of the task.
   */
  public UUID getId() {
    return id;
  }

  /**
   * Adds a comment to the task.
   *
   * @param comment The comment to add.
   */
  public void addComment(Comment comment) {
    comments.add(comment);
  }
}
