package org.mypack.services;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.mypack.dao.AbstractDao;
import org.mypack.model.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;

public abstract class AbstractService<T extends AbstractEntity> {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	protected static Hibernate3BeanReplicator replicator = new Hibernate3BeanReplicator();
	
	protected abstract AbstractDao<T> dao();
	
	@Transactional
	public T getEntity(Integer id) {
		return replicator.deepCopy(dao().getEntity(id));
	}
	
	@Transactional
	public void saveOrUpdate(T entity) {
		dao().saveOrUpdate(entity);
	}
	
	@Transactional
	public void delete(T entity) {
		dao().delete(entity);
	}
	
	@Transactional
	public List<T> getList(Criterion condition) {
		return replicator.deepCopy(dao().getList(condition));
	}
	
	public List<T> getList() {
		return replicator.deepCopy(dao().getList());
	}
}
