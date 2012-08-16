package org.mypack.dao;

import java.util.List;

import org.mypack.model.SubjectInGroupEntity;

public class SubjectInGroupEntityDao extends AbstractDao<SubjectInGroupEntity> {
	
	public SubjectInGroupEntityDao(){
		super(SubjectInGroupEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<SubjectInGroupEntity> getSubjectsInGroup(final Integer groupId){
		return getHibernateTemplate().find(
				"FROM SubjectInGroup s WHERE s.group.id = ?",
				groupId
				);
	}

	@SuppressWarnings("unchecked")
	public List<SubjectInGroupEntity> getGroupsForSubject(final Integer subjectId){
		return getHibernateTemplate().find(
				"FROM SubjectInGroup s WHERE s.item.id = ?",
				subjectId
				);
	}
	
}
