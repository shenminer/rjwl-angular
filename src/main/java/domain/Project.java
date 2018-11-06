package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import controller.view.ProjectDetailedView;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Table(name = "t_project")
@Entity
public class Project {
    @Id
    @GeneratedValue
    private int projectId;
    @Column
    private String title;
    @Lob
    private String detail;
    @Column
    private String startTime;
    @Column
    private String endTime;
    @Column
    private int status;
    @ManyToOne
    @JsonIgnoreProperties({"no", "role", "level", "weChatId", "weChat", "qq", "tel",
            "gender", "email", "eduStartDate", "graduate", "firstTeacherId", "secondTeacherId"})
    @ProjectDetailedView
    private User principal;
    @Column
    @ProjectDetailedView
    private String acceptance;
    @Column
    private String type;
    @ManyToOne
    @JsonIgnoreProperties({"no", "role", "level", "weChatId", "weChat", "qq", "tel",
            "gender", "email", "eduStartDate", "graduate", "firstTeacherId", "secondTeacherId"})
    private User instructor;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    @JsonIgnoreProperties({"project"})
    @ProjectDetailedView
    private List<Mission> missions;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }
}
