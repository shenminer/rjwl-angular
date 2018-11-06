package service;

import common.ICurdServiceSupport;
import domain.User;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IUserService extends ICurdServiceSupport<User> {
    String TEACHER = "teacher";
    String STUDENT = "student";

    void register(User user);

    User getByWeChatId(String weChatId);

    User findByNoName(String no, String name);

    void updateIgnoreWeChatId(User user);
}
