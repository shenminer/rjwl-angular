package domain;

import javax.persistence.*;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_assessment")
public class Assessment {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private int allowance;
    @Column
    private int project;
    @Column
    private int paper;

    @Column
    private int e_allowance;
    @Column
    private int service;
    @ManyToOne
    private User student;
    @Column
    private String time;
    @Column
    private int commit;
    @Column
    private int month;
    @Column
    private int year;
    @Column
    private String baseRank;
    @Column
    private String projectRank;
    @Column
    private String paperRank;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public int getE_allowance() {
        return e_allowance;
    }

    public void setE_allowance(int e_allowance) {
        this.e_allowance = e_allowance;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommit() {
        return commit;
    }

    public void setCommit(int commit) {
        this.commit = commit;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getBaseRank() {
        return baseRank;
    }

    public void setBaseRank(String baseRank) {
        this.baseRank = baseRank;
    }

    public String getProjectRank() {
        return projectRank;
    }

    public void setProjectRank(String projectRank) {
        this.projectRank = projectRank;
    }

    public String getPaperRank() {
        return paperRank;
    }

    public void setPaperRank(String paperRank) {
        this.paperRank = paperRank;
    }
}
