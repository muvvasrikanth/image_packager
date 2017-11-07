package com.qs.image_packager;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.qs.image_packager.configuration.ImagePackagerConfiguration;
import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.domain.ProductImageMetadata;
import com.qs.image_packager.service.ImageDownloadServices;
import com.qs.image_packager.util.XmlUtil;
import com.sun.mail.smtp.SMTPAddressFailedException;

@Component("processor")
public class ImagePackagerProcessor implements ItemProcessor<ImageDownloadJob, ImageDownloadJob> {

	private static final Logger logger = Logger.getLogger(ImagePackagerProcessor.class) ;
	private static final int BYTES_PER_MB = 1024 * 1024 ;
	
	private ZipOutputStream zipOutputStream ;
	
	private AmazonS3 s3Client ;

	@Autowired
	private ImagePackagerConfiguration configuration ;
	
	@Autowired
	private ImageDownloadServices service ;
	
	@Autowired
	private JavaMailSender mailSender ;
	
	@Override
	public ImageDownloadJob process(ImageDownloadJob job) throws Exception {
		Integer maxZipFileSizeBytes = configuration.getMaxZipFileSize()*BYTES_PER_MB ;
		logger.info(XmlUtil.toXml(job));
		logger.info("MaxZipFileSize = " + maxZipFileSizeBytes);
		
		try{
			job.setStatus(ImageDownloadJob.IN_PROCESS);
			service.updateImageDownloadJob(job);
			
			String zipFileBaseName = job.getCustomerNumber() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime()) + "_" ; 
			URL url = null ;
			BufferedInputStream bis = null ;
			ZipEntry zipEntry = null ;
			ZipOutputStream zipOutputStream = null ;
			File zipFile = null ;
			int index = 0, zipSize = 0, imageIndex = 0 ;
			Boolean force = false ;
			String zipFileName = null ;
			s3Client = new AmazonS3Client(new BasicAWSCredentials(configuration.getS3AccessKey(), configuration.getS3SecretKey())) ;
		
			List <ProductImageMetadata> piMetadata = service.getProductImageMetaData(job) ;
			logger.info("There are (" + piMetadata.size() + ") results");
			
			if(piMetadata.size() > 0){
				for(ProductImageMetadata pim : piMetadata){
					if(null == zipFileName || force ){
						zipFileName = zipFileBaseName + indexToString(index) + ".zip" ;
					}
					
					logger.info("ZipFile name = " + configuration.getFsTempPath() + zipFileName);
					zipOutputStream = getZipOutputStream(configuration.getFsTempPath() + zipFileName, force);
					zipEntry = new ZipEntry(pim.getFileName()) ;
					zipOutputStream.putNextEntry(zipEntry);
					logger.info("Looking for the image at: " + configuration.getBaseUrl() + pim.getS3Path());
					url = new URL(configuration.getBaseUrl() + pim.getS3Path()) ;
					
					bis = new BufferedInputStream(url.openStream()) ;
					BufferedImage bim = ImageIO.read(bis) ;
					ImageIO.write(bim, "jpg", zipOutputStream) ;
					
					zipOutputStream.closeEntry();
					imageIndex++  ;
					
					zipFile = new File(configuration.getFsTempPath() + zipFileName) ;
					if(zipFile.length() > maxZipFileSizeBytes || imageIndex == piMetadata.size()){
						zipOutputStream.close() ;
						force = true ;
						zipSize = 0 ;
						index++ ;
						logger.info("Sending the zip file (" + zipFileName + ") to " + configuration.getS3BucketName());
						getS3Client().putObject(configuration.getS3BucketName(), zipFileName, zipFile) ;
						job.getZipFileNames().add(zipFile.getName()) ;
					} 
				}
				
				job.setStatus(ImageDownloadJob.ZIP_FILES_CREATED);
				job.setStatusMessage("Zip files created") ;
			} else {
				job.setStatus(ImageDownloadJob.FAILED);
				job.setStatusMessage("No images found for criteria list");
			}
		} catch (ConnectException ce){
			job.setStatus(ImageDownloadJob.NEW);
			job.setStatusMessage(ce.getMessage());
			service.updateImageDownloadJob(job);
			logger.error(ce.getMessage(),ce);
		} catch (Exception e) {
			job.setStatus(ImageDownloadJob.ERROR);
			job.setStatusMessage(e.getMessage());
			service.updateImageDownloadJob(job);
			logger.error(e.getMessage(), e);
		}
		
		try
		{
			createAndSendClientEmail(job) ;
		} catch (Exception e){
			job.setStatus(ImageDownloadJob.ERROR);
			job.setStatusMessage(e.getMessage());
			service.updateImageDownloadJob(job) ;
			logger.error(e.getMessage(), e) ;
		}
				
		return job ;
	}
	
	private void createAndSendClientEmail(ImageDownloadJob job) throws JAXBException {
		logger.info("Building the email for " + job.getEmail() + " job reference: " + job.getId());
		Calendar now = Calendar.getInstance() ;
		now.add(Calendar.YEAR, 1) ;
		Date expireDate = now.getTime() ;
		SimpleMailMessage email = new SimpleMailMessage() ;
		email.setTo(job.getEmail()) ;
		email.setFrom("quiksilver-b2b@quiksilver.com");

		StringBuilder builder = new StringBuilder() ;
		logger.info("Job Status = " + job.getStatus() + " :: " + job.getStatusMessage());
		if(ImageDownloadJob.ZIP_FILES_CREATED.equals(job.getStatus())){
			email.setSubject("Quiksilver product images you requested") ;
			builder.append("We prepared the product images you requested. They are available at the following link(s).\n\n") ;
			builder.append("If you have any problems retrieving the image files please contact your Customer Service Representative with the id (").append(job.getId()).append(")\n\n") ;
			builder.append("These links will remain valid for 72 hours\n\n") ;

			GeneratePresignedUrlRequest generatePresignedUrlRequest ; 
			URL url ;
			for(String imageFileName : job.getZipFileNames()){
				generatePresignedUrlRequest = new GeneratePresignedUrlRequest(configuration.getS3BucketName(), imageFileName) ;
				generatePresignedUrlRequest.setMethod(HttpMethod.GET);
				generatePresignedUrlRequest.setExpiration(expireDate) ;
				url = s3Client.generatePresignedUrl(generatePresignedUrlRequest) ; 
				
				builder.append(url.toExternalForm()).append("\n\n") ;
			}
			
		} else {
			email.setSubject("Quiksilver product images you requested - FAILED") ;
			email.setBcc(configuration.getSupportEmailAddress());
			builder.append("We attempted to prepare the images you requested, but were unable to complete the request.\n\n") ;
			builder.append("Please contact your Customer Service Representative with the id (").append(job.getId()).append(").\n\n") ;
			builder.append("Status Message: ").append(job.getStatusMessage()).append("\n\n") ;
		}
		
		builder.append("Thank you,\n\nQuiksilver B2B Team\n\n") ;

		email.setText(builder.toString()) ;

		logger.info("Sending the email for " + job.getEmail() + " job reference: " + job.getId());

		try{
			mailSender.send(email);
			job.setStatus(ImageDownloadJob.EMAIL_SENT);
			job.setStatusMessage("Email Sent");
		} catch(Exception e){
			logger.error(e.getMessage(), e) ;
			job.setStatus(ImageDownloadJob.ERROR);
			job.setStatusMessage(e.getMessage());
		}
		
		service.updateImageDownloadJob(job);

	}

	private AmazonS3 getS3Client(){
		if(s3Client == null){
			s3Client = new AmazonS3Client(new ProfileCredentialsProvider()) ;
		}
		return s3Client ;
	}
	
	private String indexToString(int index) {
		String strIndex = "0000" + index ;
		return strIndex.substring(strIndex.length() - 4) ;
	}

	public ZipOutputStream getZipOutputStream(String zipFileName, Boolean force) throws FileNotFoundException{
		if(this.zipOutputStream == null || force){
			FileOutputStream fos = new FileOutputStream(zipFileName) ;
			BufferedOutputStream bos = new BufferedOutputStream(fos) ;
			this.zipOutputStream = new ZipOutputStream(bos) ;
		}
		return this.zipOutputStream ;
	}
	
	public void setMailSender(JavaMailSenderImpl mailSender){
		this.mailSender = mailSender ;
	}
}
