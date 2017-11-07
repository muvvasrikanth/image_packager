package com.qs.image_packager.service;

import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.domain.ProductImageMetadata;

public interface ImageDownloadServices {

	public List<ProductImageMetadata> getProductImageMetaData(ImageDownloadJob job) throws JAXBException, SQLException ;
	
	public void updateImageDownloadJob(ImageDownloadJob job) throws JAXBException ;
	
}
