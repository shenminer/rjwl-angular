package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import controller.view.MissionDetailedView;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_mission")
public class Mission {
    @Id
    @GeneratedValue
    private int missionId;
    @Column
    private String title;
    @Column
    private int status;
    @Lob
    private String detail;
    @Column
    private String startTime;
    @Column
    private String endTime;
    @ManyToOne
    @JsonIgnoreProperties({"no", "role", "level", "weChatId", "weChat", "qq", "tel",
            "gender", "email", "eduStartDate", "graduate", "firstTeacherId", "secondTeacherId"})
    private User principal;
    @Column
    private String acceptance;
    @Column
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"no", "role", "level", "weChatId", "weChat", "qq", "tel",
            "gender", "email", "eduStartDate", "graduate", "firstTeacherId", "secondTeacherId"})
    @MissionDetailedView
    private User instructor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"missions", "principal"})
    @MissionDetailedView
    private Project project;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "mission")
    @MissionDetailedView
    private List<Weekly> weeklies;

    public List<Weekly> getWeeklies() {
        return weeklies;
    }

    public void setWeeklies(List<Weekly> weeklies) {
        this.weeklies = weeklies;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
