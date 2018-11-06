package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_weekly")
public class Weekly {
    @Id
    @GeneratedValue
    private int weeklyId;
    @ManyToOne
    @JsonIgnoreProperties({"no", "role", "level", "weChatId", "weChat", "qq", "tel",
            "gender", "email", "eduStartDate", "graduate", "firstTeacherId", "secondTeacherId"})
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;
    @Lob
    private String content;
    @Column
    private String others;
    @Column
    private String date;
    @JsonIgnore
    @OneToMany(mappedBy = "weekly", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(int weeklyId) {
        this.weeklyId = weeklyId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @JsonIgnore
    public Mission getMission() {
        return mission;
    }

    @JsonProperty
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
