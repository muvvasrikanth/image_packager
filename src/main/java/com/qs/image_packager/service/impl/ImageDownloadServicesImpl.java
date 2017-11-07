package com.qs.image_packager.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qs.image_packager.dao.ImageDownloadDao;
import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.domain.ProductImageMetadata;
import com.qs.image_packager.service.ImageDownloadServices;
import com.qs.image_packager.util.XmlUtil;

@Service
public class ImageDownloadServicesImpl implements ImageDownloadServices {

	private static final Logger logger = Logger.getLogger(ImageDownloadServicesImpl.class) ;
	
	@Autowired
	private ImageDownloadDao dao;
	
	@Override
	public List<ProductImageMetadata> getProductImageMetaData(
			ImageDownloadJob job) throws JAXBException, SQLException {
		
		logger.info("Fetching images for: " + XmlUtil.toXml(job));
		
		List<ProductImageMetadata> list = dao.getProductImageMetadata(job.getStylesAsList()) ;
		
		logger.info("Found (" + list.size() + ") images for the job");
		
		return list ;
		
	}

	@Override
	public void updateImageDownloadJob(ImageDownloadJob job) throws JAXBException {
		logger.info("Updating the job (" + XmlUtil.toXml(job) + ")") ;
		
		dao.updateImageDownloadJob(job) ;
		
	}

}
