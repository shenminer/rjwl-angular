package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Conference;
import org.springframework.stereotype.Repository;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class ConferenceDao extends AbstractHibernateCurdDaoSupport<Conference> implements IConferenceDao {
}
