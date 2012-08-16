package org.mypack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@SuppressWarnings("serial")
@Entity
@Table(name="subjectsancestors")
public class SubjectAncestorsEntity extends AbstractEntity {

	@Override
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="levelid")
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="itemid")
	public SubjectEntity getItem() {
		return item;
	}
	public void setItem(SubjectEntity item) {
		this.item = item;
	}
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="groupid")
	public SubjectEntity getGroup() {
		return group;
	}
	public void setGroup(SubjectEntity group) {
		this.group = group;
	}
	
	private Integer id;
	private Integer levelId;
	private SubjectEntity item;
	private SubjectEntity group;
}
