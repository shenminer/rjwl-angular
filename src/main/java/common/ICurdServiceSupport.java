package common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;


public interface ICurdServiceSupport<T> {

    public abstract T get(Serializable id);

    public abstract void insert(T entity);

    public abstract void update(T entity);

    public abstract void merge(T entity);

    public abstract void saveOrUpdate(T entity);

    public abstract void delete(T entity);

    public abstract T deleteById(Serializable id);

    public abstract List<T> findAll();

    public abstract List<T> find(DetachedCriteria expression);

    public abstract Long getCount(DetachedCriteria expression);

    public abstract Long getCount();

    public abstract Page<T> findPage(DetachedCriteria expression, int start, int limit);

    public abstract Page<T> findPage(int start, int limit);
}
