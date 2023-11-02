import java.util.ArrayList;

/**
 * The ProjectList class represents a list of projects in a project management system.
 * It provides methods to add, remove, get, and retrieve all projects in the list.
 */
public class ProjectList {

  private ArrayList<Project> projects;

  // Private constructor to enforce the Singleton pattern
  private ProjectList() {
    projects = Database.getProjects();
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
  public ArrayList<Project> addProject(String name) {
    Project project = new Project(name);
    projects.add(project);
    return new ArrayList<>(projects);
  }

  public void saveProjects() {
    Database.saveProjects(projects);
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

  /**
   * Adds an existing project to the project list.
   *
   * @param project The project to add.
   * @return true if the project was added successfully, false if a project with the same ID already exists.
   */
  // public boolean addProject(Project project) {
  //   for (Project existingProject : projects) {
  //     if (existingProject.getId().equals(project.getId())) {
  //       return false; // Project with the same ID already exists
  //     }
  //   }
  //   projects.add(project);
  //   return true;
  // }
}
