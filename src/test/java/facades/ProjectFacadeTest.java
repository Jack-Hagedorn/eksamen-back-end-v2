package facades;

import entities.Developer;
import entities.Project;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.*;

class ProjectFacadeTest {

    private static Developer d1, d2, d3;
    private static Project p1, p2, p3;

    private static EntityManagerFactory emf;
    private static ProjectFacade projectFacade;
    private static DeveloperFacade developerFacade;

    public ProjectFacadeTest(){

    }


    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        projectFacade = ProjectFacade.getProjectFacade(emf);
        developerFacade = DeveloperFacade.getDeveloperFacade(emf);
    }

    @BeforeEach
    public void setUp(){
        EntityManager em = emf.createEntityManager();
        d1 = new Developer("Alpha", "alpha@developer.test", "123", 200);
        d2 = new Developer("Beta", "beta@developer.test", "456", 180);
        d3 = new Developer("Charlie", "Charlie@developer.test", "789", 160);

        p1 = new Project("Big Project", "Big Project text");
        p2 = new Project("Medium Project", "Medium Project text");
        p3 = new Project("Small Project", "Small Project text");

        try{
            em.getTransaction().begin();
            em.createQuery("delete from Project").executeUpdate();
            em.createQuery("delete from Developer").executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    @Test
    void getProjects() {
        assertEquals(3, projectFacade.getProjects().size());
    }

    @Test
    void addProject() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            projectFacade.addProject("New project", "New project test");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(4, projectFacade.getProjects().size());
    }

    @Test
    void addDeveloper() {
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            TypedQuery<Project> projectQuery = em.createQuery("SELECT p FROM Project p where p.id=:id", Project.class);
            projectQuery.setParameter("id", p1.getId());
            TypedQuery<Developer> developerQuery = em.createQuery("SELECT d FROM Developer d where d.id=:id", Developer.class);
            developerQuery.setParameter("id", d1.getId());
            Project p = projectQuery.getSingleResult();
            Developer d = developerQuery.getSingleResult();

            p.addDeveloper(d);
            em.merge(p);
            em.getTransaction().commit();
            assertEquals(1, p.getDevelopers().size());
        } finally {
            em.close();
        }

    }
}