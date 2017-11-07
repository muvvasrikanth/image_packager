package com.qs.image_packager.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.qs.image_packager.domain.ImageDownloadJob;

public class XmlUtilTest {
	
	private static final Logger logger = Logger.getLogger(XmlUtilTest.class) ;

	@Test
	public void test() throws JAXBException, ParseException {
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><imageDownloadJob><downloadDate>2014-07-16T00:00:00-07:00</downloadDate><email>someone@somewhere.com</email><id>1</id><lastActiveDate>2014-07-16T00:00:00-07:00</lastActiveDate><s3Path></s3Path><status>N</status><statusMessage>Ready for Processing</statusMessage><styles>303284-DKR, ADBS300033-IND, 303266-BLK</styles></imageDownloadJob>" ;
		String actual = XmlUtil.toXml(mockImageDownloadJob()) ;
		logger.info(actual);
		assertNotNull(actual) ;
		assertEquals(expected, actual) ;
	}

	private ImageDownloadJob mockImageDownloadJob() throws ParseException {
		ImageDownloadJob idj = new ImageDownloadJob() ;
		idj.setId(1);
		idj.setEmail("someone@somewhere.com");
		idj.setDownloadDate(new SimpleDateFormat("yyyyMMdd").parse("20140716"));
		idj.setStyles("303284-DKR, ADBS300033-IND, 303266-BLK");
		idj.setS3Path("");
		idj.setStatus("N");
		idj.setStatusMessage("Ready for Processing");
		idj.setLastActiveDate(new SimpleDateFormat("yyyyMMdd").parse("20140716"));
		return idj ;
	}

}
