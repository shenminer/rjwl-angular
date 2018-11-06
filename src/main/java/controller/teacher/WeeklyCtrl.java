package controller.teacher;

import domain.User;
import domain.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import service.IWeeklyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hhx on 2017/4/6.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("weekly")
public class WeeklyCtrl {
    @Autowired
    private IWeeklyService weeklyService;

    @GET
    @Path("{id}")
    public Weekly get(@PathParam("id") int id) {
        return weeklyService.get(id);
    }

    @GET
    @Path("find")
    public List<Weekly> find(@QueryParam("studentId") int id) {
        User user = new User();
        user.setUserId(id);
        return weeklyService.findByStudent(user);
    }

    @GET
    @Path("smy")
    public List<Weekly> find(@QueryParam("studentId") int id, @QueryParam("year") int year, @QueryParam("month") int month) {
        User user = new User();
        user.setUserId(id);
        return weeklyService.findByStudentAndMonth(user, year, month);
    }

    @GET
    @Path("smy2")
    public List<Weekly> find2(@QueryParam("studentId") int id, @QueryParam("year") int year, @QueryParam("month") int month) {
        User user = new User();
        user.setUserId(id);
        List<Weekly> preWeekly = weeklyService.findByStudentAndMonth(user, year, month);
        List<Weekly> postWeekly = weeklyService.findByStudentAndMonth(user, year, month + 1);
        preWeekly.addAll(postWeekly);
        return preWeekly;
    }
}
