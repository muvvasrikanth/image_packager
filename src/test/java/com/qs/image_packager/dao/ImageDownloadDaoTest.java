package com.qs.image_packager.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;

import com.qs.image_packager.dao.impl.ImageDownloadDaoImpl;
import com.qs.image_packager.domain.ProductImageMetadata;

@ContextConfiguration(locations={"/test-launch-context.xml"})
@RunWith(MockitoJUnitRunner.class)
public class ImageDownloadDaoTest {
	
	@InjectMocks
	private ImageDownloadDao dao = new ImageDownloadDaoImpl() ;
	
	@Mock
	private JdbcTemplate template ;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAutowiring() throws DataAccessException, ParseException, SQLException {
		Mockito.when(template.query(Mockito.anyString(), (RowMapper<ProductImageMetadata>)Mockito.any())).thenReturn(mockProductImageMetadataList()) ;
		List<ProductImageMetadata> actual = dao.getProductImageMetadata(mockStyles()) ;
		assertNotNull(actual) ;
		assertEquals(actual.size(), 2) ;
	}

	private List<ProductImageMetadata> mockProductImageMetadataList() throws ParseException {
		List <ProductImageMetadata> list = new ArrayList<ProductImageMetadata>() ;
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

	private List<String> mockStyles(){
		List <String> styles = new ArrayList <String> () ;
		styles.add("303284-DKR") ;
		styles.add("ADBS300033-IND") ;
		styles.add("303266-BLK") ;
		return styles ;
	}
}
