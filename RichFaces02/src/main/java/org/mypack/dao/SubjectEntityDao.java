package org.mypack.dao;

import org.mypack.model.SubjectEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectEntityDao extends AbstractDao<SubjectEntity> {
	
	public SubjectEntityDao() {
		super(SubjectEntity.class);
	}

}
