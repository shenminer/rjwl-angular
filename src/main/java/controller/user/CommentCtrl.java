package controller.user;

import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import service.ICommentService;
import service.IUserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("comment")
public class CommentCtrl {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICommentService commentService;

    @POST
    public String add(Comment comment) {
        comment.setTimestamp(System.currentTimeMillis());
        commentService.insert(comment);
        return "success";
    }
}
