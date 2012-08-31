package org.mypack.services;

import java.util.List;

import org.mypack.dao.AbstractDao;
import org.mypack.dao.SubjectEntityDao;
import org.mypack.model.SubjectEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectEntityService extends AbstractService<SubjectEntity> {

	@Autowired
	private SubjectEntityDao dao;
	
	@Override
	protected AbstractDao<SubjectEntity> dao() {
		return dao;
	}

	@Transactional
	public List<SubjectEntity> getSubjectsInGroup(final Integer groupId) {
		return dao.getSubjectsInGroup(groupId);
	}
}
