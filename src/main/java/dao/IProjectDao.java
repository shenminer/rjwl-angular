package dao;

import common.ICurdDaoSupport;
import domain.Project;
import domain.User;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IProjectDao extends ICurdDaoSupport<Project> {
    List<Project> findRelatedProject(User user);
}
