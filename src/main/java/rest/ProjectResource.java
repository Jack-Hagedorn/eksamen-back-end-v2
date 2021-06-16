package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDTO;
import entities.Developer;
import entities.Project;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("project")
public class ProjectResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final ProjectFacade FACADE = ProjectFacade.getProjectFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public Response getAllProjects(){return Response.ok(GSON.toJson(FACADE.getProjects())).build(); }

    @GET
    public String serverIsUp() {
        return "{\"msg\":\"Your Person API is up and running\"}";
    }

    @POST
    public String addProject(String project){
        ProjectDTO p = GSON.fromJson(project, ProjectDTO.class);
        ProjectDTO pNew = FACADE.addProject(p.getName(), p.getDescription());
        return GSON.toJson(pNew);
    }

    @PUT
    @Path("{projectid}")
    public String addDeveloper(@PathParam("projectid") String project, String developer){
        Developer d = GSON.fromJson(developer, Developer.class);
        Project p = GSON.fromJson(project, Project.class);
        FACADE.addDeveloper(p, d);
        return "Developer Added";
    }

}
