package service;

import common.ICurdServiceSupport;
import domain.Project;
import domain.User;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IProjectService extends ICurdServiceSupport<Project> {

    void changeStatus(Project project);

    List<Project> findRelatedProject(User user);
}
