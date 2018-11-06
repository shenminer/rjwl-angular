package dao;

import common.ICurdDaoSupport;
import domain.Assessment;
import domain.User;

/**
 * Created by hhx on 2017/4/2.
 */

public interface IAssessmentDao extends ICurdDaoSupport<Assessment> {
    Assessment getLatestByStudent(User user);
}
