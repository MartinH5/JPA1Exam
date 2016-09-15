/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProjectHandler implements IProjectHandler {
    
    private final String persistenceUnit;

    public ProjectHandler(String persistenceUnit) {
        this.persistenceUnit = persistenceUnit;
    }
    
    @Override
    public void createUser(String name, String email) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        ProjectUser pu = new ProjectUser();
        pu.setUserName(name);
        pu.setEmail(email);
        
        entitymanager.persist(pu);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }

    @Override
    public ProjectUser findUser(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager entitymanager = emfactory.createEntityManager();
        ProjectUser pu = entitymanager.find(ProjectUser.class, id);
        return pu;
    }

    @Override
    public List<ProjectUser> getAllUsers() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager entitymanager = emfactory.createEntityManager();
        TypedQuery<ProjectUser> pUsers = entitymanager.createNamedQuery("ProjectUser.findAll", ProjectUser.class);
        return pUsers.getResultList();
    }

    @Override
    public void createProject(String name, String description) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        
        entitymanager.persist(project);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }

    @Override
    public void assignUserToProject(ProjectUser user, int projectId) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Project p = findProject(projectId);
        p.setProjectUser(user);
        
        entitymanager.persist(p);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }

    @Override
    public Project findProject(int id) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);
        EntityManager entitymanager = emfactory.createEntityManager();
        Project p = entitymanager.find(Project.class, id);
        return p;
    }

    @Override
    public void CreateTaskAndAssignToProject(String name, String description, int hoursAssigned, int hoursUsed, int projectId) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(persistenceUnit);

        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Task t = createTask(name, description, hoursAssigned, hoursUsed);
        
        Project p = findProject(projectId);
        p.setTasks(t);
        
        entitymanager.persist(p);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }
    
    private Task createTask(String name, String description, int hoursAssigned, int hoursUsed) {
        Task t = new Task();
        t.setName(name);
        t.setDescription(description);
        t.setHoursAssigned(hoursAssigned);
        t.setHoursUsed(hoursUsed);
        return t;
    }
    
}
