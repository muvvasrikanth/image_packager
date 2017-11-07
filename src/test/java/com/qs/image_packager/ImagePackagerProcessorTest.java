package com.qs.image_packager;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;

import com.qs.image_packager.configuration.ImagePackagerConfiguration;
import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.domain.ProductImageMetadata;
import com.qs.image_packager.service.ImageDownloadServices;

@ContextConfiguration(locations={"/test-launch-context.xml"})
@RunWith(MockitoJUnitRunner.class)
public class ImagePackagerProcessorTest {
	
	@Mock
	private ImageDownloadServices service ;
	
	@Mock
	private ImagePackagerConfiguration configuration ;
	
	@Mock
	private JavaMailSender mailSender ;

	@InjectMocks
	private ImagePackagerProcessor processor = new ImagePackagerProcessor() ;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAutowiring() {
		assertNotNull(processor) ;
	}

	@Test
	public void testProcess() throws Exception{
		when(configuration.getBaseUrl()).thenReturn("https://d22s0gjkad48ui.cloudfront.net/") ;
		when(configuration.getFsTempPath()).thenReturn("C:/temps/imagepackager/") ;
		when(configuration.getMaxZipFileSize()).thenReturn(new Integer(20)) ;
		when(configuration.getS3BucketName()).thenReturn("quikconnect-qa.image.downloads") ;
		when(configuration.getS3AccessKey()).thenReturn("AKIAIE7QAD2RU3NFTZMQ") ;
		when(configuration.getS3SecretKey()).thenReturn("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") ;
		when(service.getProductImageMetaData(any(ImageDownloadJob.class))).thenReturn(mockProductImageMetadataList()) ;
		ImageDownloadJob after = processor.process(mockImageDownloadJob()) ;
		assertNotNull(after) ;
	}
	
	private List<ProductImageMetadata> mockProductImageMetadataList() throws ParseException {
		List <ProductImageMetadata> list = new ArrayList <ProductImageMetadata> () ;
		ProductImageMetadata pim0 = new ProductImageMetadata() ;
		pim0.setId("1000000");
		pim0.setStyleNumber("452N13") ;
		pim0.setColorCode("PPH");
		pim0.setSize(1800);
		pim0.setAngle("DTL4");
		pim0.setCAD(Boolean.FALSE);
		pim0.setLineBook(Boolean.FALSE);
		pim0.setFileName("452N13_PPH_DTL4_1800-2398.JPG");
		pim0.setS3Path("products/13/452N13/452N13_PPH_DTL4_1800-2398.JPG");
		pim0.setUploadDate(new SimpleDateFormat("yyyyMMdd").parse("20140717"));
		pim0.setJobCode("INIT_DUMP");
		pim0.setCreatedDateTime(new SimpleDateFormat("yyyyMMdd").parse("20140717"));
		pim0.setModifiedDateTime(new SimpleDateFormat("yyyyMMdd").parse("20140717"));
		pim0.setImageType("OTHERS");
		pim0.setOldJobCode("INIT_DUMP");
		pim0.setMaterialNumber("452N13-PPH");
		list.add(pim0) ;
		ProductImageMetadata pim1 = new ProductImageMetadata() ;
		pim1.setId("1000003");
		pim1.setStyleNumber("452N13") ;
		pim1.setColorCode("PPH");
		pim1.setSize(150);
		pim1.setAngle("FRT1");
		pim1.setCAD(Boolean.FALSE);
		pim1.setLineBook(Boolean.FALSE);
		pim1.setFileName("452N13_PPH_FRT1_150-192.JPG");
		pim1.setS3Path("products/13/452N13/452N13_PPH_FRT1_150-192.JPG");
		pim1.setUploadDate(new SimpleDateFormat("yyyyMMdd").parse("20140717"));
		pim1.setJobCode("INIT_DUMP");
		pim1.setCreatedDateTime(new SimpleDateFormat("yyyyMMdd").parse("20140717"));
		pim1.setModifiedDateTime(new SimpleDateFormat("yyyyMMdd").parse("20140717"));
		pim1.setImageType("MEDIUM");
		pim1.setOldJobCode("INIT_DUMP");
		pim1.setMaterialNumber("452N13-PPH");
		list.add(pim1) ;
		return list ;
	}

	private ImageDownloadJob mockImageDownloadJob() throws ParseException {
		ImageDownloadJob idj = new ImageDownloadJob() ;
		idj.setId(1);
		idj.setCustomerNumber("1000044");
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
