package service;

import common.AbstractCurdServiceSupport;
import common.ICurdDaoSupport;
import dao.ICommentDao;
import domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hhx on 2017/4/2.
 */
@Service
public class CommentService extends AbstractCurdServiceSupport<Comment> implements ICommentService {
    @Autowired
    private ICommentDao commentDao;

    @Override
    protected ICurdDaoSupport<Comment> getCurdDao() {
        return commentDao;
    }
}
