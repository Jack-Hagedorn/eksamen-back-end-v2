package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DeveloperFacade;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
