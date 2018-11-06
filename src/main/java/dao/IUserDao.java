package dao;

import common.ICurdDaoSupport;
import domain.User;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IUserDao extends ICurdDaoSupport<User> {
    User getByWeChatId(String weChatId);

}
