import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a column in a project management system, holding a list of tasks.
 */
public class Column {

    
    private final String title; // The title of the column
    private final ArrayList<Task> tasks; // The list of tasks in the column
    private final ArrayList<Column> columns;

    /**
     * Constructs a new Column with the given title.
     *
     * @param title the title of the column
     */
    public Column(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    /**
     * Adds a task to the column.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the column based on its UUID.
     *
     * @param taskId the UUID of the task to be removed
     * @return true if the task was removed successfully, false otherwise
     */
    public boolean removeTask(UUID taskId) {
        return tasks.removeIf(task -> task.getId().equals(taskId));
    }

    /**
     * Updates a task in the column based on its UUID.
     *
     * @param taskId      the UUID of the task to be updated
     * @param updatedTask the updated task
     * @return true if the task was updated successfully, false otherwise
     */
    public boolean updateTask(UUID taskId, Task updatedTask) {
        int index = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(taskId)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            tasks.set(index, updatedTask);
            return true;
        }
        return false;
    }

    /**
     * Retrieves a task from the column based on its UUID.
     *
     * @param taskId the UUID of the task to retrieve
     * @return the task if found, null otherwise
     */
    public Task getTaskById(UUID taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    /**
     * Gets the tasks in the column.
     *
     * @return a list of tasks in the column
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    // public boolean moveTaskTo(Column targetColumn, UUID taskId) {
    //     Task taskToMove = getTaskById(taskId);
    //     if (taskToMove != null) {
    //         if (targetColumn != this) {
    //             targetColumn.addTask(taskToMove);
    //             tasks.remove(taskToMove);
    //         }
    //         return true;
    //     }
    //     return false;
    // }

    /**
     * Gets the title of the column.
     *
     * @return the title of the column
     */
    public String getTitle() {
        return title;
    }
}
