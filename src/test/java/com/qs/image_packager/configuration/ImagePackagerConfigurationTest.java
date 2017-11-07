package com.qs.image_packager.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"/test-launch-context.xml"})
public class ImagePackagerConfigurationTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private ImagePackagerConfiguration configuration ;
	
	@Test
	public void testAutowiring(){
		assertNotNull(configuration) ;
	}

	@Test
	public void testGetBaseUrl() {
		String expected = "https://d22s0gjkad48ui.cloudfront.net/" ;
		String actual = configuration.getBaseUrl() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
		expected = "https://somewhere.com/service/" ;
		actual = configuration.getS3ServiceUrl() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
		expected = "https://quikconnect-qa.image.downloads.s3-website-us-east-1.amazonaws.com/" ;
		actual = configuration.getS3BucketUrl() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
		expected = "quikconnect-qa.image.downloads" ;
		actual = configuration.getS3BucketName() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
		expected = "C:/temps/imagepackager/" ;
		actual = configuration.getFsTempPath() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
		Integer intExpected = 20 ;
		Integer intActual = configuration.getMaxZipFileSize() ;
		assertNotNull(intActual) ;
		assertEquals(intExpected, intActual) ;
		expected = "AKIAIE7QAD2RU3NFTZMQ" ;
		actual = configuration.getS3AccessKey() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
		expected = "2bFh0jVhvGqt87YX+6euHbDyPnp4nFX62sfsIpfu" ;
		actual = configuration.getS3SecretKey() ;
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
	}

}
