import java.util.ArrayList;
import java.util.Date;



public class Task {
    private String name;
    private User assignedUser;
    private ArrayList<Comment> comments;
    private ArrayList<String> history = new ArrayList<>();
    private Integer priority;
    private Date timeWorkedOn;
    private double hours;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.assignedUser = null; // Initialize assignedUser to null
        this.comments = new ArrayList<>();
        this.priority = 0; // Default priority
        this.timeWorkedOn = new Date();
        this.hours = 0.0; // Initialize hours worked to 0
        this.status = false; // Task starts as not completed
    }

    public void trackTime(double hoursWorked) {
        if (status) {
            // Add the hours worked to the existing total
            hours += hoursWorked;
            timeWorkedOn = new Date(); // Update the last time worked on
        } else {
            System.out.println("Task is not in progress. Start the task before tracking time.");
        }
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void trackChanges(String attribute, String newValue) {
        if (status) {
            String changeLog = "Attribute '" + attribute + "' changed to '" + newValue + "' at " + new Date();
            System.out.println(changeLog);
        } else {
            System.out.println("Task is not in progress. Start the task before tracking changes.");
        }
    }

    public void saveHistory(String event) {
        history.add(event);
    }

    public void printHistory() {
        for (String event : history) {
            System.out.println(event);
        }
    }

    public void autoSave() {
        // You can implement auto-saving logic here
        // For example, periodically save the task's data.
    }

    public void markAsCompleted() {
        status = true;
        timeWorkedOn = new Date(); // Set the completion time
    }
}