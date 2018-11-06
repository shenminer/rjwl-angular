package domain;

import javax.persistence.*;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_notice")
public class Notice {
    @Id
    @GeneratedValue
    private int id;
    @Lob
    private String content;
    @Column
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
