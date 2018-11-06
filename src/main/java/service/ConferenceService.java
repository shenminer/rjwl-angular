package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IConferenceDao;
import domain.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class ConferenceService extends AbstractCurdServiceSupport<Conference> implements IConferenceService {
    @Autowired
    private IConferenceDao conferenceDao;

    @Override
    protected ICurdDaoSupport<Conference> getCurdDao() {
        return conferenceDao;
    }
}
