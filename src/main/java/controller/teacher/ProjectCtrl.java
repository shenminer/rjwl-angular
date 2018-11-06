package controller.teacher;

import controller.BaseCtrl;
import controller.UnauthorizedException;
import controller.view.MissionDetailedView;
import controller.view.ProjectDetailedView;
import domain.Project;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.IProjectService;
import service.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("project")
public class ProjectCtrl extends BaseCtrl {
    @Autowired
    private IProjectService projectService;


    @GET
    @Path("/detail/{id}")
    @ProjectDetailedView
    public Project get(@PathParam("id") int id) {
        return projectService.get(id);
    }

    @GET
    public List<Project> find(@HeaderParam("token") String token) {
        User user = getUser();
        return projectService.findRelatedProject(user);
    }

    @POST
    public String add(Project project) {
        project.setStatus(1);
        projectService.insert(project);
        return "success";
    }

    @PUT
    @Path("status")
    public String changeStatus(Project project) {
        projectService.changeStatus(project);
        return "success";
    }

    @PUT
    public String update(Project project) {
        projectService.update(project);
        return "success";
    }

    @DELETE
    @Path("{id}")
    public String delete(@HeaderParam("token") String token, @PathParam("id") int id) throws UnauthorizedException {
        Project origin = projectService.get(id);
        User user = getUser();
        if (!user.getLevel().equals("1") && user.getUserId() != origin.getPrincipal().getUserId()) {
            throw new UnauthorizedException();
        }
        projectService.deleteById(id);
        return "success";
    }
}
