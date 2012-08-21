package org.mypack.services;

import org.mypack.dao.AbstractDao;
import org.mypack.dao.SubjectInGroupEntityDao;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectInGroupEntityService extends AbstractService<SubjectInGroupEntity> {
	@Autowired
	private SubjectInGroupEntityDao dao;
	
	@Override
	protected AbstractDao<SubjectInGroupEntity> dao() {
		return dao;
	}
}
