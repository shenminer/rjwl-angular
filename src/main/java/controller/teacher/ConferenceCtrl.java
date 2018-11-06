package controller.teacher;

import domain.Conference;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import service.IConferenceService;

import javax.persistence.GeneratedValue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hhx on 2017/4/8.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("conference")
public class ConferenceCtrl {
    @Autowired
    private IConferenceService conferenceService;

    @GET
    @Path("{id}")
    public Conference get(@PathParam("id") int id) {
        return conferenceService.get(id);
    }

    @GET
    @Path("find")
    public List<Conference> find() {
        DetachedCriteria dc = DetachedCriteria.forClass(Conference.class);
        dc.addOrder(Order.desc("timestamp"));
        return conferenceService.find(dc);
    }

    @POST
    public String add(Conference conference) {
        conference.setTimestamp(System.currentTimeMillis());
        conferenceService.insert(conference);
        return "success";
    }

    @PUT
    public String update(Conference conference) {
        Conference origin = conferenceService.get(conference.getId());
        origin.setUserName(conference.getUserName());
        origin.setContent(conference.getContent());
        conferenceService.update(origin);
        return "success";
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") int id) {
        conferenceService.deleteById(id);
        return "success";
    }
}
