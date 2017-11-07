package com.qs.image_packager.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ParameterBuilderTest {
	
	private static final Logger logger = Logger.getLogger(ParameterBuilderTest.class) ;
	
	@Test
	public void testListToSqlParameter() {
		String expected = "'303296A-BO1', '303284-MI0', 'ADBS300034-5BD', '303310A-BK5', 'ADBS300033-CA6'" ;
		String actual = ParameterBuilder.listToSqlParameter(mockStyleList()) ;
		logger.info("Style parameter: " + actual) ;
		assertNotNull(actual) ;
		assertEquals(actual, expected) ;
	}

	private List<String> mockStyleList() {
		List <String> list = new ArrayList <String> () ;
		list.add("303296A-BO1") ;
		list.add("303284-MI0") ;
		list.add("ADBS300034-5BD") ;
		list.add("303310A-BK5") ;
		list.add("ADBS300033-CA6") ;
		return list ;
	}

}
