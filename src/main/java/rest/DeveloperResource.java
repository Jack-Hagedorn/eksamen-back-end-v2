package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DeveloperDTO;
import facades.DeveloperFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("developer")
public class DeveloperResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final DeveloperFacade FACADE = DeveloperFacade.getDeveloperFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("all")
    public Response getAllDevelopers(){
        return Response.ok(GSON.toJson(FACADE.getDevelopers())).build();
    }

    @GET
    public String serverIsUp() {
        return "{\"msg\":\"Your Person API is up and running\"}";
    }
}
