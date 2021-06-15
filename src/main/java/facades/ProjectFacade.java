package facades;

import dtos.DeveloperDTO;
import dtos.ProjectDTO;
import entities.Developer;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectFacade {

    private static EntityManagerFactory emf;
    private static ProjectFacade instance;

    private ProjectFacade(){
    }

    /**
     * @Param _emf
     * @Return the instance of this facade
     */
    public static ProjectFacade getProjectFacade(EntityManagerFactory _emf){
        if (instance == null){
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    public List<ProjectDTO> getProjects(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Project> q = em.createQuery("SELECT p FROM Project p", Project.class);
            return q.getResultList().stream().map(ProjectDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    public ProjectDTO addProject(String name, String description){
        EntityManager em = emf.createEntityManager();

        Project project = new Project(name, description);

        try{

            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ProjectDTO(project);
    }

    public void addDeveloper(String projectid, String developerId){
        EntityManager em = emf.createEntityManager();

        try{

            TypedQuery<Project> qP = em.createQuery("SELECT p FROM Project p WHERE p.id =:id", Project.class);
            qP.setParameter("id", Long.parseLong(projectid));
            TypedQuery<Developer> qD = em.createQuery("SELECT d FROM Developer d where d.id =:id", Developer.class);
            qD.setParameter("id", Long.parseLong(developerId));

            em.getTransaction().begin();
            if(qP.getSingleResult().getDevelopers().contains(qD.getSingleResult())){
                em.close();
            } else {
                qP.getSingleResult().addDeveloper(qD.getSingleResult());
                em.merge(qP.getSingleResult());
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }

    }
}
