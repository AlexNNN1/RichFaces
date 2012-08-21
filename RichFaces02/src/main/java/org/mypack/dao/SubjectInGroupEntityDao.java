package org.mypack.dao;

import java.util.List;

import org.mypack.model.SubjectAncestorsEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectInGroupEntityDao extends AbstractDao<SubjectInGroupEntity> {
	
	public SubjectInGroupEntityDao(){
		super(SubjectInGroupEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<SubjectInGroupEntity> getSubjectsInGroup(final Integer groupId){
		return getHibernateTemplate().find(
				"FROM SubjectInGroupEntity s WHERE s.group.id = ?",
				groupId
				);
	}

	@SuppressWarnings("unchecked")
	public List<SubjectInGroupEntity> getGroupsForSubject(final Integer subjectId){
		return getHibernateTemplate().find(
				"FROM SubjectInGroupEntity s WHERE s.item.id = ?",
				subjectId
				);
	}
	
	@SuppressWarnings("unchecked")
	public List<SubjectInGroupEntity> getForTreeFiller(final Integer rootId) {
		return getHibernateTemplate().find(
				"FROM SubjectInGroupEntity s WHERE exists " +
				"(FROM SubjectAncestorsEntity a WHERE " +
				"(s.item.id = a.item.id AND a.group.id = ?) OR (a.item.id = ?))",
				rootId, rootId);
	}
}
