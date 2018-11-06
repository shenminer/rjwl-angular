package controller.user;

import controller.BaseCtrl;
import controller.UnauthorizedException;
import domain.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by hhx on 2017/4/2.
 */
@Path("user")
public class UserCtrl extends BaseCtrl {
    @Autowired
    private IUserService userService;


    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public User getUser() {
        return super.getUser();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(User user) throws UnauthorizedException {
        String msg;
        User origin = getUser();
        if (origin.getUserId() != user.getUserId()) {
            throw new UnauthorizedException();
        }
        userService.update(user);
        msg = "update success";
        return String.valueOf(msg);
    }

}
