import java.util.ArrayList;

/**
 * The ProjectList class represents a list of projects in a project management system.
 * It provides methods to add, remove, get, and retrieve all projects in the list.
 */
public class ProjectList {
    private ArrayList<Project> projects;

    // Private constructor to enforce the Singleton pattern
    private ProjectList() {
        projects = new ArrayList<>();
    }

    private static ProjectList projectList;

    /**
     * Get the instance of the ProjectList using the Singleton pattern.
     *
     * @return The ProjectList instance.
     */
    public static ProjectList getInstance() {
        if (projectList == null) {
            projectList = new ProjectList();
        }
        return projectList;
    }

    /**
     * Adds a new project to the list.
     *
     * @param project The project to be added.
     * @return true if the project was added successfully, false otherwise.
     */
    public boolean addProject(String name) {
        Project newProject = new Project(name);
        return projects.add(newProject);
    }

    /**
     * Removes a project from the list.
     *
     * @param project The project to be removed.
     * @return true if the project was removed successfully, false otherwise.
     */
    public boolean removeProject(String name) {
        Project projectToRemove = null;
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                projectToRemove = project;
                break;
            }
        }
        return projectToRemove != null && projects.remove(projectToRemove);
    }

    /**
     * Retrieves a project from the list by its name.
     *
     * @param name The name of the project to be retrieved.
     * @return The project with the specified name, or null if not found.
     */
    public Project getProject(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of all projects in the project list.
     *
     * @return An ArrayList containing all projects in the list.
     */
    public ArrayList<Project> getAllProjects() {
        return projects;
    }
}
