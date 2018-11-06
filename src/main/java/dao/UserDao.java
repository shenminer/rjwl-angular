package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class UserDao extends AbstractHibernateCurdDaoSupport<User> implements IUserDao {
    @Override
    public User getByWeChatId(String weChatId) {
        String hql = "from User u where u.weChatId=?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, weChatId);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
