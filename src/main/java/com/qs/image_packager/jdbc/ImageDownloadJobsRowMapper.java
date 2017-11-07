package com.qs.image_packager.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qs.image_packager.domain.ImageDownloadJob;

public class ImageDownloadJobsRowMapper implements RowMapper<ImageDownloadJob> {

	@Override
	public ImageDownloadJob mapRow(ResultSet rs, int i)
			throws SQLException {
		
		ImageDownloadJob idj = new ImageDownloadJob() ;
		
		idj.setId(rs.getInt("ID"));
		idj.setDownloadDate(rs.getDate("DOWNLOAD_DATE"));
		idj.setEmail(rs.getString("EMAIL"));
		idj.setCustomerNumber(rs.getString("CUSTOMER_NUMBER"));
		idj.setStyles(rs.getString("STYLES"));
		idj.setS3Path(rs.getString("S3_PATH"));
		idj.setStatus(rs.getString("STATUS"));
		idj.setStatusMessage(rs.getString("STATUS_MESSAGE"));
		idj.setLastActiveDate(rs.getDate("LAST_ACTIVE_DATE"));
		idj.setJobCode(rs.getString("JOB_CODE"));
		
		return idj ;
	}

}
