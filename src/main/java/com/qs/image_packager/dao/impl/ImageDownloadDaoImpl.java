package com.qs.image_packager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.qs.image_packager.dao.ImageDownloadDao;
import com.qs.image_packager.domain.ImageDownloadJob;
import com.qs.image_packager.domain.ProductImageMetadata;
import com.qs.image_packager.jdbc.ProductImageMetadataRowMapper;
import com.qs.image_packager.util.ParameterBuilder;

@Component("dao")
public class ImageDownloadDaoImpl implements ImageDownloadDao {
	
	private static final Logger logger = Logger.getLogger(ImageDownloadDaoImpl.class); 
	
	@Autowired
	private DataSource dataSource ;
	
	@Autowired
	private JdbcTemplate template ;

	@Override
	public List<ProductImageMetadata> getProductImageMetadata(
			List<String> styles) throws SQLException {
		
		String styleParam = ParameterBuilder.listToSqlParameter(styles) ;
		String sql = "SELECT * FROM bgx_product_images WHERE material_number in (" + styleParam + ") and FILE_NAME like '%_1260-%'" ;
		logger.info("Executing : " + sql);
		return (List<ProductImageMetadata>) template.query(sql, new ProductImageMetadataRowMapper()) ;
		
	}

	@Override
	public void updateImageDownloadJob(ImageDownloadJob job) {
		
		template.update("update bgx_image_download_jobs set status=?, status_message=? where id=?", job.getStatus(), 
				(job.getStatusMessage().length() >= 128 ? job.getStatusMessage().substring(0, 127) : job.getStatusMessage()), 
				job.getId()) ;
	}
	
}
