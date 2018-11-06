package domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_conference")
public class Conference {
    @Id
    @GeneratedValue
    private int id;
    @Lob
    private String content;
    @Column
    private long timestamp;
    @Column
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
