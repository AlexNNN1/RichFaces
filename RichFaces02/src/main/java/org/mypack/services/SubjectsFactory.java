package org.mypack.services;

import java.util.LinkedList;
import java.util.List;

import org.mypack.dao.SubjectAncestorsEntityDao;
import org.mypack.dao.SubjectEntityDao;
import org.mypack.dao.SubjectInGroupEntityDao;
import org.mypack.helpers.Constants;
import org.mypack.model.SubjectAncestorsEntity;
import org.mypack.model.SubjectEntity;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
public class SubjectsFactory {

	@Autowired
	private SubjectEntityDao subjects;
	@Autowired
	private SubjectInGroupEntityDao subjectsInGroups;
	@Autowired
	private SubjectAncestorsEntityDao subjectsAncestors;

	@Transactional
	public SubjectEntity createSubject(String name, Integer groupId) {
		return addSubject(name, Constants.subjectLeave, groupId);
	}

	private SubjectEntity addSubject(String name, Integer levelId,
			Integer groupId) {
		SubjectEntity subject = createSubjectEntity(name, levelId);
		subjects.saveOrUpdate(subject);
		addSubjectInGroups(subject, groupId);
		return subject;
	}

	private void addSubjectInGroups(SubjectEntity subject, Integer groupId) {
		SubjectEntity group = subjects.getEntity(groupId);
		if (group != null && groupId != 0) {
			addInGroup(subject, group);
			createAncestorsChain(group, subject);
		}
	}

	private SubjectEntity createSubjectEntity(String name, Integer levelId) {
		SubjectEntity subject = new SubjectEntity();
		subject.setName(name);
		subject.setLevelId(levelId);
		subject.setStatus(Constants.subjectEnabled);
		return subject;
	}

	private void addInGroup(SubjectEntity subject, SubjectEntity group) {
		SubjectInGroupEntity link = new SubjectInGroupEntity();
		link.setGroup(group);
		link.setItem(subject);
		link.setLevelId(Constants.subjectLink);
		subjectsInGroups.saveOrUpdate(link);
	}

	private void createAncestorsChain(SubjectEntity parent, SubjectEntity child) {
		List<SubjectInGroupEntity> ancestors = createAncestorsList(child
				.getId());
		for (SubjectInGroupEntity ancestor : ancestors) {
			if (ancestor.getGroup() != null) {
				SubjectAncestorsEntity rec = new SubjectAncestorsEntity();
				rec.setGroup(ancestor.getGroup());
				rec.setItem(ancestor.getItem());
				rec.setLevelId(Constants.subjectLink);
				subjectsAncestors.saveOrUpdate(rec);
			}
		}
	}

	private List<SubjectInGroupEntity> createAncestorsList(Integer subjectId) {
		List<SubjectInGroupEntity> result = new LinkedList<SubjectInGroupEntity>();
		List<SubjectInGroupEntity> list = subjectsInGroups
				.getGroupsForSubject(subjectId);
		System.out.println("first = " + list.size());
		if (list != null) {
			for (SubjectInGroupEntity item : list) {
				SubjectInGroupEntity rec = new SubjectInGroupEntity();
				rec.setItem(item.getItem());
				rec.setGroup(item.getGroup());
				getSubjectAncestors(rec.getItem().getId(), rec.getGroup()
						.getId(), result);
				result.add(rec);
			}
		}
		return result;
	}

	private void getSubjectAncestors(Integer subjectId, Integer parentID,
			List<SubjectInGroupEntity> result) {
		List<SubjectInGroupEntity> list = subjectsInGroups
				.getGroupsForSubject(parentID);
		System.out.println("in 1 = " + list.size());
		if (list != null) {
			System.out.println("in 2 = " + list.size());
			for (SubjectInGroupEntity item : list) {
				SubjectInGroupEntity rec = new SubjectInGroupEntity();
				rec.setItem(item.getItem());
				rec.setGroup(item.getGroup());
				result.add(rec);
				if (item.getGroup() != null && item.getItem() != null) {
					getSubjectAncestors(rec.getItem().getId(), rec.getGroup()
							.getId(), result);
				}

			}
		}
	}

}
