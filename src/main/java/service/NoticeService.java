package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.INoticeDao;
import domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class NoticeService extends AbstractCurdServiceSupport<Notice> implements INoticeService {
    @Autowired
    private INoticeDao noticeDao;

    @Override
    protected ICurdDaoSupport<Notice> getCurdDao() {
        return noticeDao;
    }
}
