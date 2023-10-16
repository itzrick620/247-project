import java.util.ArrayList;
import java.util.Date;
import javax.xml.stream.events.Comment;


import javax.xml.stream.events.Comment;

public class Task {
    private String name;
    private User assignedUser;
    private ArrayList<Comment> comments;
    private Integer priority;
    private Date timeWorkedOn;
    private double hours;
    private boolean status;

    public void trackTime() {}

    public void setPriority(Integer priority) {}

    public void trackChanges() {}

    public void saveHistory() {}

    public void autoSave() {}

    public void markAsCompleted() {}
}
