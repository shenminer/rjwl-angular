package common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface ICurdDaoSupport<T> {

    public abstract T get(Serializable id);

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void saveOrUpdate(T entity);

    public abstract void merge(T entity);

    public abstract void delete(T entity);

    public abstract List<T> findAll();

    public abstract List<T> find(final DetachedCriteria detachedCriteria);

    public abstract Long getCount(final DetachedCriteria detachedCriteria);

    public abstract Long getCount();

    public abstract Page<T> findPage(final DetachedCriteria detachedCriteria,
                                     final int start, final int limit);

    public abstract Page<T> findPage(final int start, final int limit);

    public abstract int bulkUpdateBySQL(final String sql, final Object... params);

    public abstract List findBySQL(final String sql, final Object... params);

}