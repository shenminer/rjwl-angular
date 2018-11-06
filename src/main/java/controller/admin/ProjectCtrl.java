package controller.admin;

import controller.view.ProjectDetailedView;
import domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import service.IProjectService;
import service.IUserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("project")
public class ProjectCtrl {
    @Autowired
    private IProjectService projectService;


    @GET
    public List<Project> find() {
        return projectService.findAll();
    }
}
