package org.mypack.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.mypack.model.AbstractEntity;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AbstractDao<T extends AbstractEntity> extends HibernateDaoSupport {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private Class<T> entityClass;
	
	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Autowired
	public void setTemplate(HibernateTemplate template) {
		super.setHibernateTemplate(template);
	}
	
	public T getEntity(Integer id) {
		return getHibernateTemplate().get(entityClass, id);
	}
	
	public void saveOrUpdate(T entity) {
		super.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void delete(T entity) {
		super.getHibernateTemplate().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(Criterion condition){
		DetachedCriteria rootCriteria = DetachedCriteria.forClass(entityClass);
		if (condition != null){
			rootCriteria.add(condition);
		}
		return getHibernateTemplate().findByCriteria(rootCriteria);
	}
	
	public List<T>getList(){
		return getList(null);
	}
}
