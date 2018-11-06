package dao;

import common.AbstractHibernateCurdDaoSupport;
import domain.Comment;
import org.springframework.stereotype.Repository;

/**
 * Created by hhx on 2017/4/2.
 */
@Repository
public class CommentDao extends AbstractHibernateCurdDaoSupport<Comment> implements ICommentDao {
}
