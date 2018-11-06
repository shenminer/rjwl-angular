package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Relationship;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class RelationshipDao extends AbstractHibernateCurdDaoSupport<Relationship> implements IRelationshipDao {
    @Override
    public Relationship getByStudentId(int id) {
        Relationship relationship = new Relationship();
        relationship.setStudentId(id);
        List<Relationship> list = getHibernateTemplate().findByExample(relationship);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
