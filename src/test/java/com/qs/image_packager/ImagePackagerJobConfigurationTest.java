package com.qs.image_packager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"/test-launch-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ImagePackagerJobConfigurationTest {
	
	private static final Logger logger = Logger.getLogger(ImagePackagerJobConfigurationTest.class); 
	
	@Autowired
	private JobLauncher jobLauncher ;
	
	@Autowired
	private Job job ;

	@Test
	public void testAutowiring() {
		assertNotNull(jobLauncher) ;
		assertNotNull(job) ;
	}

	@Test
	public void testJobLauncher() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
//		JobExecution exec = jobLauncher.run(job, new JobParameters()) ;
//		assertNotNull(exec) ;
//		List <Throwable> exceptions = exec.getAllFailureExceptions() ;
//		assertNotNull(exceptions) ;
//		
//		if(exceptions.size() > 0){
//			for(Throwable t : exceptions){
//				logger.warn(t.getMessage());
//			}
//			fail("Exceptions were returned by the job launcher") ;
//		}
//		
//		BatchStatus status = exec.getStatus() ;
//		assertNotNull(status) ;
//		logger.info(status.toString());
//		assertTrue(status.equals(BatchStatus.COMPLETED)) ;
	}
	
}
