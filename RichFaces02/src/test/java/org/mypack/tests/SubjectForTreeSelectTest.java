package org.mypack.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.mypack.dao.SubjectInGroupEntityDao;
import org.mypack.model.SubjectInGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SubjectForTreeSelectTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private SubjectInGroupEntityDao subjectInGroups;

	@Test
	public void testTreeSelect(){
		List<SubjectInGroupEntity> values = subjectInGroups.getForTreeFiller(5);
	//	System.out.println("test list size=" + values.size());
		for (SubjectInGroupEntity entity : values) {
			System.out.println(entity.getItem().getName());
			System.out.print(entity.getItem().getId());
			System.out.println(entity.getGroup());
		}
		assertTrue(values.size() > 1);
	}
}
