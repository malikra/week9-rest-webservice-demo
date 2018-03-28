package edu.matc.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.matc.entity.User;
import edu.matc.persistence.UserDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("/hello")
public class RestServiceToJson {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response getHelloMessage(@PathParam("id") String id) {
        //Return a single message
        //String message = "Hello world this is my first restful service !!!" + " " + id;
        UserDao userDao = new UserDao();
        User user = userDao.getById(Integer.parseInt(id));
        return Response.status(200).entity(user.toString()).build();

    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyBean() throws JsonProcessingException {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getAllUsers();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //1. Convert List of Person objects to JSON
        String arrayToJson = objectMapper.writeValueAsString(users.toString());
        return Response.status(200).entity(arrayToJson).build();
    }
}
