package org.mypack.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mypack.model.SubjectEntity;
import org.mypack.services.SubjectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SubjectEntityTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	static Logger logger = Logger.getLogger(SubjectFactoryTest.class);
	
	@Autowired
	private SubjectEntityService subjects;
	
	@Test
	public void getSubjectsForGroup() {
		List<SubjectEntity> values = subjects.getSubjectsInGroup(56);
		assertTrue(values.size() > 5);
	}
	
}
