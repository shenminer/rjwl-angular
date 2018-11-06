package controller.anon;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by hhx on 2017/4/2.
 */
@Path("user")
public class UserCtrl {
    @Autowired
    private IUserService userService;

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(User user) {
        userService.register(user);
        return String.valueOf("success");
    }
*/
}
