package dao;

import common.ICurdDaoSupport;
import domain.Relationship;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IRelationshipDao extends ICurdDaoSupport<Relationship> {
    Relationship getByStudentId(int id);
}
