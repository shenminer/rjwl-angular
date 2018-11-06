package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.User;
import domain.Weekly;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class WeeklyDao extends AbstractHibernateCurdDaoSupport<Weekly> implements IWeeklyDao {
    @Override
    public List<Weekly> findByStudent(User user, int year, int month) {
        String hql = "from Weekly w where w.student=? and w.date like ? group by w.date";
        String m = String.valueOf(month);
        if (m.length() == 1) {
            m = "0" + m;
        }
        String date = year + "-" + m + "%";
        return (List<Weekly>) getHibernateTemplate().find(hql, user, date);
    }

    @Override
    public List<Weekly> findByStudent(User usr) {
        String hql = "from Weekly w where w.student.userId=? order by w.weeklyId desc ";
        return (List<Weekly>) getHibernateTemplate().find(hql, usr.getUserId());
    }
}
