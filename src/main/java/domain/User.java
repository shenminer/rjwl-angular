package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by hhx on 2017/4/2.
 */
@Table(name = "t_user", indexes = {@Index(columnList = "weChatId")})
@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;
    @Column(unique = true)
    private String no;
    @Column
    private String name;
    @Column(nullable = false)
    private String role;
    @Column
    private String level;
    @Column
    private String weChatId;
    @Column
    private String weChat;
    @Column
    private String qq;
    @Column
    private String tel;
    @Column
    private String gender;
    @Column
    private String email;
    @Column
    private String eduStartDate;
    @Column
    private String graduate;
    @Transient
    private int firstTeacherId;
    @Transient
    private int secondTeacherId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return ((User) obj).getUserId() == this.getUserId();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        return new HashCodeBuilder(getUserId() % 2 == 0 ? getUserId() + 1 : getUserId(), PRIME).toHashCode();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    @JsonIgnore
    public String getWeChat() {
        return weChat;
    }

    @JsonProperty
    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEduStartDate() {
        return eduStartDate;
    }

    public void setEduStartDate(String eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
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
