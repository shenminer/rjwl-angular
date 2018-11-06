package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IMissionDao;
import dao.IProjectDao;
import domain.Mission;
import domain.Project;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class ProjectService extends AbstractCurdServiceSupport<Project> implements IProjectService {
    @Autowired
    private IProjectDao projectDao;
    @Autowired
    private IMissionDao missionDao;

    @Override
    protected ICurdDaoSupport<Project> getCurdDao() {
        return projectDao;
    }

    @Override
    @Transactional
    public void changeStatus(Project project) {
        Project origin = projectDao.get(project.getProjectId());
        origin.setStatus((origin.getStatus() == 1) ? 0 : 1);
        projectDao.update(origin);
        List<Mission> missions = origin.getMissions();
        for (int i = 0; i < missions.size(); i++) {
            missions.get(i).setStatus((missions.get(i).getStatus() == 1) ? 0 : 1);
        }
        missionDao.batchUpdate(missions);
    }

    @Override
    @Transactional
    public void update(Project entity) {
        Project origin = projectDao.get(entity.getProjectId());
        origin.setAcceptance(entity.getAcceptance());
        origin.setDetail(entity.getDetail());
        origin.setEndTime(entity.getEndTime());
        origin.setStartTime(entity.getStartTime());
        origin.setInstructor(entity.getInstructor());
        origin.setTitle(entity.getTitle());
        origin.setType(entity.getType());
        origin.setPrincipal(entity.getPrincipal());
        projectDao.update(origin);
    }

    @Override
    public List<Project> findRelatedProject(User user) {
        return projectDao.findRelatedProject(user);
    }
}
