import java.util.ArrayList;
import java.util.Date;

/**
 * The Task class represents a task with various properties and methods for tracking and managing tasks.
 */
public class Task {
    private String name;
    private User assignedUser;
    private ArrayList<Comment> comments;
    private ArrayList<String> history = new ArrayList<>();
    private Integer priority;
    private Date timeWorkedOn;
    private double hours;
    private boolean status;

    /**
     * Constructs a new Task object with a given name.
     * 
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.assignedUser = null;
        this.comments = new ArrayList<>();
        this.priority = 0;
        this.timeWorkedOn = new Date();
        this.hours = 0.0;
        this.status = false;
    }

    /**
     * Constructs a new Task object with a name, priority, assigned user, and status.
     * 
     * @param name         The name of the task.
     * @param priority     The priority of the task.
     * @param assignedUser The user assigned to the task.
     * @param status       The status of the task.
     */
    public Task(String name, Integer priority, User assignedUser, boolean status) {
        this.name = name;
        this.priority = priority;
        this.assignedUser = assignedUser;
        this.status = status;
        this.comments = new ArrayList<>();
        this.timeWorkedOn = new Date();
        this.hours = 0.0;
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
            System.out.println("Task is not in progress. Start the task before tracking time.");
        }
    }

    /**
     * Sets the priority of the task.
     * 
     * @param priority The priority to set for the task.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * Tracks changes made to the task's attributes.
     * 
     * @param attribute The attribute that was changed.
     * @param newValue  The new value of the attribute.
     */
    public void trackChanges(String attribute, String newValue) {
        if (status) {
            String changeLog = "Attribute '" + attribute + "' changed to '" + newValue + "' at " + new Date();
            System.out.println(changeLog);
        } else {
            System.out.println("Task is not in progress. Start the task before tracking changes.");
        }
    }

    /**
     * Saves a historical event related to the task.
     * 
     * @param event The event to save in the task's history.
     */
    public void saveHistory(String event) {
        history.add(event);
    }

    /**
     * Prints the history of events related to the task.
     */
    public void printHistory() {
        for (String event : history) {
            System.out.println(event);
        }
    }

    /**
     * Performs an automatic save operation for the task's data.
     */
    public void autoSave() {
        // You can implement auto-saving logic here
        // For example, periodically save the task's data.
    }

    /**
     * Marks the task as completed and records the completion time.
     */
    public void markAsCompleted() {
        status = true;
        timeWorkedOn = new Date(); // Set the completion time
    }
}
