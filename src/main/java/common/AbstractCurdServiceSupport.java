package common;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractCurdServiceSupport<T> implements ICurdServiceSupport<T> {

    protected abstract ICurdDaoSupport<T> getCurdDao();

    @Override
    @Transactional
    public void delete(T entity) {
        getCurdDao().delete(entity);
    }

    @Override
    @Transactional
    public T deleteById(Serializable id) {
        T entity = get(id);
        getCurdDao().delete(entity);
        return entity;
    }

    @Override
    public List<T> find(DetachedCriteria expression) {
        return getCurdDao().find(expression);
    }

    @Override
    public List<T> findAll() {
        return getCurdDao().findAll();
    }

    @Override
    public Page<T> findPage(DetachedCriteria expression, int start, int limit) {
        return getCurdDao().findPage(expression, start, limit);
    }

    @Override
    public T get(Serializable id) {
        return getCurdDao().get(id);
    }

    @Override
    public Long getCount(DetachedCriteria expression) {
        return getCurdDao().getCount(expression);
    }

    @Override
    @Transactional
    public void insert(T entity) {
        getCurdDao().insert(entity);
    }

    @Override
    @Transactional
    public void merge(T entity) {
        getCurdDao().merge(entity);
    }

    @Override
    @Transactional
    public void saveOrUpdate(T entity) {
        getCurdDao().saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        getCurdDao().update(entity);
    }

    @Override
    public Long getCount() {
        return getCurdDao().getCount();
    }

    @Override
    public Page<T> findPage(int start, int limit) {
        return getCurdDao().findPage(start, limit);
    }

}
