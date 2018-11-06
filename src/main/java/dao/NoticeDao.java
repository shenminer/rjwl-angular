package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Notice;
import org.springframework.stereotype.Repository;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class NoticeDao extends AbstractHibernateCurdDaoSupport<Notice> implements INoticeDao {
}
