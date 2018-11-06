package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private int commentId;
    @Column
    private String content;
    @Column
    private long timestamp;
    @ManyToOne
    @JsonIgnoreProperties({"comments"})
    private Weekly weekly;
    @ManyToOne
    private User user;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Weekly getWeekly() {
        return weekly;
    }

    public void setWeekly(Weekly weekly) {
        this.weekly = weekly;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
