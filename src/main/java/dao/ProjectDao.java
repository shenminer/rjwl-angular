package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Project;
import domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class ProjectDao extends AbstractHibernateCurdDaoSupport<Project> implements IProjectDao {
    @Override
    public List<Project> findRelatedProject(User user) {
        String hql = "from Project p where p.principal=? or p.instructor=?";
        return (List<Project>) getHibernateTemplate().find(hql, user, user);
    }
}
