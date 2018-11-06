package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Assessment;
import domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class AssessmentDao extends AbstractHibernateCurdDaoSupport<Assessment> implements IAssessmentDao {
    @Override
    public Assessment getLatestByStudent(User user) {
        String hql = "from Assessment a where a.student=? order by a.id desc limit 1";
        return (Assessment) getHibernateTemplate().find(hql, user);
    }
}
