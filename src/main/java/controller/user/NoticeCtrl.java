package controller.user;

import domain.Notice;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import service.INoticeService;

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
@Path("notice")
public class NoticeCtrl {
    @Autowired
    private INoticeService noticeService;

    @GET
    public List<Notice> find() {
        DetachedCriteria dc = DetachedCriteria.forClass(Notice.class);
        dc.addOrder(Order.desc("id"));
        return noticeService.find(dc);
    }

    @POST
    public String add(Notice notice) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        notice.setDate(df.format(new Date()));
        noticeService.insert(notice);
        return "success";
    }
}
