package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.IWeeklyDao;
import domain.User;
import domain.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class WeeklyService extends AbstractCurdServiceSupport<Weekly> implements IWeeklyService {
    @Autowired
    private IWeeklyDao weeklyDao;

    @Override
    protected ICurdDaoSupport<Weekly> getCurdDao() {
        return weeklyDao;
    }

    @Override
    public List<Weekly> findByStudentAndMonth(User student, int year, int month) {
        List<Weekly> weeklies = weeklyDao.findByStudent(student, year, month);
        return weeklies;
    }

    @Override
    public List<Weekly> findByStudent(User usr) {
        return weeklyDao.findByStudent(usr);
    }
}
