package controller.teacher;

import domain.Assessment;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import service.IAssessmentService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hhx on 2017/4/2.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Path("assessment")
public class AssessmentCtrl {
    @Autowired
    private IAssessmentService assessmentService;

    @Path("excel")
    @GET
    @Produces({"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public StreamingOutput excel(final OutputStream outputStream,@QueryParam("year") int year, @QueryParam("month") int month) {
        return (OutputStream output) ->
                assessmentService.export(output, year, month);
    }
    @Path("excelFile")
    @GET
    public String excelFile(@Context HttpServletRequest request, @QueryParam("year") int year, @QueryParam("month") int month) {

        String url = request.getServletContext().getRealPath("/") ;
        String fileName = "";
        try {
            fileName = assessmentService.exportFile(url,year,month);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @GET
    @Path("smy")
    @Produces(MediaType.APPLICATION_JSON)
    public Assessment findByUserIdAndMonth(@QueryParam("studentId") int studentId, @QueryParam("year") int year, @QueryParam("month") int month) {
        DetachedCriteria dc = DetachedCriteria.forClass(Assessment.class);
        dc.createAlias("student", "s");
        dc.add(Restrictions.eq("s.userId", studentId));
        dc.add(Restrictions.eq("month", month));
        dc.add(Restrictions.eq("year", year));
        dc.addOrder(Order.desc("id"));
        List<Assessment> list = assessmentService.find(dc);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String saveOrUpdate(Assessment assessment) {
        assessmentService.assess(assessment);
        return "success";
    }
}
