import java.util.UUID;
import java.util.ArrayList;
public class Project {
    private UUID id;
    private String name;
    private String footnotes;
    private User owner;
    private User scrumMaster;
    private ArrayList<Comment> comment;
    private ArrayList<User> developers;

    public Project(String name){}

    public Project(UUID id, String name){}

    public void addTask(Task task){}

    public void removeTask(Task task){}

    public void share(){}

    public void addFootnotes(){}
}
