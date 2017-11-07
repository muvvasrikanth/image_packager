package com.qs.image_packager.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.qs.image_packager.domain.ProductImageMetadata;

public class ProductImageMetadataRowMapper implements RowMapper<ProductImageMetadata> {

	@Override
	public ProductImageMetadata mapRow(ResultSet rs, int i)
			throws SQLException {
		ProductImageMetadata pim = new ProductImageMetadata() ;
		pim.setId(rs.getString("ID"));
		pim.setStyleNumber(rs.getString("STYLE_NUMBER"));
		pim.setColorCode(rs.getString("COLOR_CODE"));
		pim.setSize(rs.getInt("SIZE"));
		pim.setAngle(rs.getString("ANGLE")) ;
		pim.setCAD(rs.getBoolean("CAD"));
		pim.setLineBook(rs.getBoolean("LINEBOOK"));
		pim.setFileName(rs.getString("FILE_NAME"));
		pim.setS3Path(rs.getString("S3_PATH"));
		pim.setUploadDate(rs.getDate("UPLOAD_DATE"));
		pim.setJobCode(rs.getString("JOB_CODE"));
		pim.setCreatedDateTime(rs.getDate("CREATED_DATETIME"));
		pim.setModifiedDateTime(rs.getDate("MODIFIED_DATETIME"));
		pim.setImageType(rs.getString("IMAGE_TYPE"));
		pim.setOldJobCode(rs.getString("OLD_JOB_CODE"));
		pim.setMaterialNumber(rs.getString("MATERIAL_NUMBER"));
		return pim ;
	}

}
