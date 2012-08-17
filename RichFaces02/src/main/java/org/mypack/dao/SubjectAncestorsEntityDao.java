package org.mypack.dao;

import org.mypack.model.SubjectAncestorsEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectAncestorsEntityDao extends AbstractDao<SubjectAncestorsEntity> {
	
	public SubjectAncestorsEntityDao() {
		super(SubjectAncestorsEntity.class);
	}

}
