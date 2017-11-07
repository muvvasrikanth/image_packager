package com.qs.image_packager;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qs.image_packager.configuration.ImagePackagerConfiguration;
import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.service.ImageDownloadServices;

@Component("writer") 
public class ImagePackagerWriter implements ItemWriter<ImageDownloadJob> {
	
	private static final Logger logger = Logger.getLogger(ImagePackagerWriter.class) ;
	
	@Autowired
	private ImagePackagerConfiguration configuration ;
	
	@Autowired
	private ImageDownloadServices service ;

	@Override
	public void write(List<? extends ImageDownloadJob> jobList) throws Exception {
		logger.info("Writing (" + jobList.size() + ") jobs");
		File tempFile ;
		for(ImageDownloadJob job : jobList){
			logger.info("Doing cleanup for job (" + job.getId() + ")");
			if(! ImageDownloadJob.ERROR.equalsIgnoreCase(job.getStatus())){
				job.setStatus(ImageDownloadJob.CLEAN_TEMP_FILES);
				job.setStatusMessage("Cleaning up temp files");
				service.updateImageDownloadJob(job);
				
				for(String zipFileName : job.getZipFileNames()){
					tempFile = new File(configuration.getFsTempPath()+zipFileName) ;
					logger.info("Deleting temp file: " + tempFile.getName());
					FileUtils.deleteQuietly(tempFile) ;
				}
				
				job.setStatus(ImageDownloadJob.COMPLETE);
				job.setStatusMessage("COMPLETE");
			}
			
			service.updateImageDownloadJob(job);
		}
	}

}
