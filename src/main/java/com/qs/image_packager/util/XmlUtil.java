package com.qs.image_packager.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlUtil {

	public static String toXml(Object o) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance(o.getClass()) ;
		Marshaller m = context.createMarshaller() ;
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		
		StringWriter writer = new StringWriter() ;
		
		m.marshal(o, writer);
		
		return writer.toString() ;
	}
}
