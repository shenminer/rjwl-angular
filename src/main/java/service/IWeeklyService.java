package service;

import common.ICurdServiceSupport;
import domain.User;
import domain.Weekly;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IWeeklyService extends ICurdServiceSupport<Weekly> {
    List<Weekly> findByStudentAndMonth(User student, int year, int month);

    List<Weekly> findByStudent(User usr);
}
