package org.mypack.dao;

import java.util.List;

import org.mypack.helpers.Constants;
import org.mypack.model.SubjectEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectEntityDao extends AbstractDao<SubjectEntity> {
	
	public SubjectEntityDao() {
		super(SubjectEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<SubjectEntity> getSubjectsInGroup(final Integer groupId) {
		return getHibernateTemplate().find(
				"FROM SubjectEntity ent WHERE exists (FROM SubjectInGroupEntity s WHERE exists " +
				"(FROM SubjectInGroupEntity a WHERE (s.item.id = a.item.id AND a.group.id = ?) AND " +
				"(s.item.id = ent.id))) AND ent.levelId = ?",
				groupId, Constants.subjectLeave); //   
	}
}
