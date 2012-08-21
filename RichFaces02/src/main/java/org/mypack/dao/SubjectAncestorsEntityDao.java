package org.mypack.dao;

import java.util.List;

import org.mypack.model.SubjectAncestorsEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectAncestorsEntityDao extends AbstractDao<SubjectAncestorsEntity> {
	
	public SubjectAncestorsEntityDao() {
		super(SubjectAncestorsEntity.class);
	}
}
