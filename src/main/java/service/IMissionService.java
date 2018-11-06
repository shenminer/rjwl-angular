package service;

import common.ICurdServiceSupport;
import domain.Mission;
import domain.User;

import java.util.List;

/**
 * Created by hhx on 2017/4/2.
 */
public interface IMissionService extends ICurdServiceSupport<Mission> {

    List<Mission> findByPrincipal(User user);

    void changeStatus(Mission mission);
}
