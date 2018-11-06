package dao;

import common.ICurdDaoSupport;
import domain.User;
import domain.Weekly;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IWeeklyDao extends ICurdDaoSupport<Weekly> {
    List<Weekly> findByStudent(User user, int year, int month);

    List<Weekly> findByStudent(User usr);
}
