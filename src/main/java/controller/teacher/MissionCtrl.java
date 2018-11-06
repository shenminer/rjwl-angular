package controller.teacher;

import domain.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import service.IMissionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by hhx on 2017/4/2.
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("mission")
public class MissionCtrl {

    @Autowired
    private IMissionService missionService;

    @PUT
    @Path("status")
    public String changeStatus(Mission mission) {
        missionService.changeStatus(mission);
        return "success";
    }

    @POST
    public String add(Mission mission) {
        mission.setStatus(1);
        missionService.insert(mission);
        return "success";
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") int id) {
        missionService.deleteById(id);
        return "success";
    }

    @PUT
    public String update(Mission mission) {
        missionService.update(mission);
        return "success";
    }
}
