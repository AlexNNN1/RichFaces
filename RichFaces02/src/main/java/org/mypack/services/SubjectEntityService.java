package org.mypack.services;

import org.mypack.dao.AbstractDao;
import org.mypack.dao.SubjectEntityDao;
import org.mypack.model.SubjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectEntityService extends AbstractService<SubjectEntity> {

	@Autowired
	private SubjectEntityDao dao;
	
	@Override
	protected AbstractDao<SubjectEntity> dao() {
		return dao;
	}

}
