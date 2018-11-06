package domain;

import javax.persistence.*;

/**
 * Created by hhx on 2017/4/2.
 */
@Entity
@Table(name = "t_relationship")
public class Relationship {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private int firstTeacherId;
    @Column
    private int secondTeacherId;
    @Column
    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstTeacherId() {
        return firstTeacherId;
    }

    public void setFirstTeacherId(int firstTeacherId) {
        this.firstTeacherId = firstTeacherId;
    }

    public int getSecondTeacherId() {
        return secondTeacherId;
    }

    public void setSecondTeacherId(int secondTeacherId) {
        this.secondTeacherId = secondTeacherId;
    }
}
