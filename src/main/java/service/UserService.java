package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IRelationshipDao;
import dao.IUserDao;
import domain.Relationship;
import domain.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class UserService extends AbstractCurdServiceSupport<User> implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRelationshipDao relationshipDao;
    @Autowired
    private Cache userCache;

    @Override
    protected ICurdDaoSupport<User> getCurdDao() {
        return userDao;
    }


    @Override
    @Transactional
    public void register(User user) {
        try {
            ((IUserDao) getCurdDao()).insert(user);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(user.getNo() + " duplicate");
        }
        if (STUDENT.equals(user.getRole())) {
            int firstTeacherId = user.getFirstTeacherId();
            int secondTeacherId = user.getSecondTeacherId();
            if (firstTeacherId != 0 || secondTeacherId != 0) {
                Relationship relationship = new Relationship();
                relationship.setFirstTeacherId(firstTeacherId);
                relationship.setSecondTeacherId(secondTeacherId);
                relationship.setStudentId(user.getUserId());
                relationshipDao.insert(relationship);
            }
        }
    }

    @Override
    public User getByWeChatId(String weChatId) {
        return userDao.getByWeChatId(weChatId);
    }

    @Override
    public User findByNoName(String no, String name) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("no", no));
        detachedCriteria.add(Restrictions.eq("name", name));
        List<User> list = find(detachedCriteria);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    @Transactional
    public void updateIgnoreWeChatId(User user) {
        User origin = userDao.get(user.getUserId());
        origin.setWeChat(user.getWeChat());
        origin.setEduStartDate(user.getEduStartDate());
        origin.setEmail(user.getEmail());
        origin.setGender(user.getGender());
        origin.setName(user.getName());
        origin.setNo(user.getNo());
        origin.setTel(user.getTel());
        origin.setQq(user.getQq());
        if (STUDENT.equals(user.getRole())) {
            Relationship relationship = relationshipDao.getByStudentId(user.getUserId());
            if (relationship == null) {
                relationship = new Relationship();
            }
            relationship.setFirstTeacherId(user.getFirstTeacherId());
            relationship.setSecondTeacherId(user.getSecondTeacherId());
            relationshipDao.saveOrUpdate(relationship);
        }
        userDao.update(origin);
        Element element = userCache.get(origin.getWeChatId());
        if (element != null) {
            userCache.remove(origin.getWeChatId());
        }
        userCache.put(new Element(origin.getWeChatId(), origin));
    }
}
