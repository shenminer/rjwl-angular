package controller.user;

import controller.BaseCtrl;
import controller.UnauthorizedException;
import domain.User;
import domain.Weekly;
import net.sf.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;
import service.IWeeklyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("weekly")
public class WeeklyCtrl extends BaseCtrl {

    @Autowired
    private IWeeklyService weeklyService;


    @GET
    @Path("{id}")
    public Weekly get(@PathParam("id") int id) throws UnauthorizedException {
        User user = getUser();
        Weekly weekly = weeklyService.get(id);
        if (!user.equals(weekly.getStudent()) && !user.getRole().equals("teacher")) {
            throw new UnauthorizedException();
        }
        return weekly;
    }

    @POST
    public String add(Weekly weekly) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        weekly.setDate(df.format(new Date()));
        weekly.setStudent(getUser());
        weeklyService.insert(weekly);
        return "success";
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") int id) throws UnauthorizedException {
        Weekly weekly = weeklyService.get(id);
        User user = getUser();
        String msg;
        if (!user.equals(weekly.getStudent())) {
            throw new UnauthorizedException();
        }
        weeklyService.deleteById(id);
        msg = "success";
        return msg;
    }

    @GET
    @Path("find")
    public List<Weekly> find() {
        User user = getUser();
        return weeklyService.findByStudent(user);
    }
}
