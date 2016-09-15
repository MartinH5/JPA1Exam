/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import entity.Project;
import entity.ProjectUser;
import java.util.List;

public interface IProjectHandler {
    
    public void createUser(String name, String email);
    public ProjectUser findUser(int id);
    public List<ProjectUser> getAllUsers();
    public void createProject(String name, String description);
    public void assignUserToProject(ProjectUser user, int projectId);
    public Project findProject(int id);
    public void CreateTaskAndAssignToProject(String name, String description, int hoursAssigned, int hoursUsed, int projectId);
}
