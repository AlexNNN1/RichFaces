package org.mypack.services;

import org.mypack.dao.AbstractDao;
import org.mypack.dao.SubjectAncestorsEntityDao;
import org.mypack.model.SubjectAncestorsEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectAncestorsEntityService extends AbstractService<SubjectAncestorsEntity> {

	@Autowired
	private SubjectAncestorsEntityDao dao;
	
	@Override
	protected AbstractDao<SubjectAncestorsEntity> dao() {
		return dao;
	}
}
