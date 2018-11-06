package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IMissionDao;
import domain.Mission;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class MissionService extends AbstractCurdServiceSupport<Mission> implements IMissionService {
    @Autowired
    private IMissionDao missionDao;

    @Override
    protected ICurdDaoSupport<Mission> getCurdDao() {
        return missionDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mission> findByPrincipal(User user) {
        return missionDao.findByPrincipal(user);
    }

    @Override
    @Transactional
    public void changeStatus(Mission mission) {
        Mission origin = missionDao.get(mission.getMissionId());
        origin.setStatus(origin.getStatus() == 1 ? 0 : 1);
        getCurdDao().update(origin);
    }

    @Override
    @Transactional
    public void update(Mission entity) {
        Mission origin = missionDao.get(entity.getMissionId());
        origin.setType(entity.getType());
        origin.setTitle(entity.getTitle());
        origin.setInstructor(entity.getInstructor());
        origin.setStartTime(entity.getStartTime());
        origin.setEndTime(entity.getEndTime());
        origin.setAcceptance(entity.getAcceptance());
        origin.setDetail(entity.getDetail());
        missionDao.update(origin);
    }
}
