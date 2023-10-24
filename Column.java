import java.util.ArrayList;

public class Column {
    private String title;
    private ArrayList<Task> tasks;

    public Column(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
