package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IRelationshipDao;
import domain.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class RelationshipService extends AbstractCurdServiceSupport<Relationship> implements IRelationshipService {
    @Autowired
    private IRelationshipDao relationshipDao;

    @Override
    protected ICurdDaoSupport<Relationship> getCurdDao() {
        return relationshipDao;
    }
}
