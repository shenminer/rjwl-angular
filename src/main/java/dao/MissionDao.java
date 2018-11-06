package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Mission;
import domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class MissionDao extends AbstractHibernateCurdDaoSupport<Mission> implements IMissionDao {
    @Override
    public List<Mission> findByPrincipal(User user) {
        String hql = "from Mission m where m.principal=? order by m.missionId desc ";
        return (List<Mission>) getHibernateTemplate().find(hql, user);
    }

    @Override
    public void batchUpdate(List<Mission> list) {
        getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Boolean>() {
            @Override
            public Boolean doInHibernate(Session session) throws HibernateException {
                for (int i = 0; i < list.size(); i++) {
                    session.update(list.get(i));
                    if (i % 10 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
                return true;
            }
        });
    }
}
