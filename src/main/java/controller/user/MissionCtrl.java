package controller.user;

import controller.BaseCtrl;
import controller.view.MissionDetailedView;
import domain.Mission;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.IMissionService;
import service.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("mission")
public class MissionCtrl extends BaseCtrl {

    @Autowired
    private IMissionService missionService;


    @GET
    public List<Mission> findMission() {
        User user = getUser();
        List<Mission> list = missionService.findByPrincipal(user);
        return list;
    }

    @GET
    @Path("detail/{id}")
    @MissionDetailedView
    public Mission get(@PathParam("id") int id) {
        return missionService.get(id);
    }
}
