package controller.teacher;

import domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import service.INoticeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("notice")
public class NoticeCtrl {
    @Autowired
    private INoticeService noticeService;



    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") int id) {
        noticeService.deleteById(id);
        return "success";
    }
}
