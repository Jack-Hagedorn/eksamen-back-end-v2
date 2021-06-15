package facades;

import dtos.ProjectHoursDTO;
import entities.ProjectHours;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectHoursFacade {

    private static EntityManagerFactory emf;
    private static ProjectHoursFacade instance;

    private ProjectHoursFacade(){
    }

    /**
     * @Param _emf
     * @Return the instance of this facade.
     */
    public static ProjectHoursFacade getProjectHoursFacade(EntityManagerFactory _emf){
        if (instance == null){
            emf = _emf;
            instance = new ProjectHoursFacade();
        }
        return instance;
    }

    public List<ProjectHoursDTO> getProjectHours() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<ProjectHours> q = em.createQuery("SELECT p FROM ProjectHours p", ProjectHours.class);
            return q.getResultList().stream().map(ProjectHoursDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

}
