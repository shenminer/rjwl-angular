package dao;

import common.ICurdDaoSupport;
import domain.Mission;
import domain.User;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IMissionDao extends ICurdDaoSupport<Mission> {
    List<Mission> findByPrincipal(User user);

    void batchUpdate(List<Mission> list);
}
