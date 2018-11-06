package controller.teacher;

import domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hhx on 2017/4/5.
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("user")
public class UserCtrl {
    @Autowired
    private IUserService userService;

    private final static String STUDENT = "student";
    private final static String TEACHER = "teacher";

    @GET
    @Path("activeStudent")
    public List<User> findActiveStudent() {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("graduate", "0"));
        dc.add(Restrictions.eq("role", STUDENT));
        dc.addOrder(Order.desc("no"));
        return userService.find(dc);
    }

    @GET
    @Path("activePrincipal")
    public List<User> findActivePrincipal() {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("graduate", "0"));
        dc.addOrder(Order.asc("no"));
        return userService.find(dc);
    }

    @GET
    @Path("teacher")
    public List<User> findTeacher() {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("role", TEACHER));
        dc.addOrder(Order.asc("no"));
        return userService.find(dc);
    }
}
