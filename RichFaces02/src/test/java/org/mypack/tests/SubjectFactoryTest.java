package org.mypack.tests;

import static org.junit.Assert.assertNotNull;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mypack.model.SubjectEntity;
import org.mypack.services.SubjectsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })//"classpath:applicationContext.xml" 
public class SubjectFactoryTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	static Logger logger = Logger.getLogger(SubjectFactoryTest.class);

	@Autowired
	private SubjectsFactory factory;
	
	@Test
	public void testSubjectAdd() {
		SubjectEntity root = factory.createSubject("root", 56);
		assertNotNull(root);
		SubjectEntity child = factory.createSubject("child", root.getId());
		assertNotNull(child);
	}	
	
}
