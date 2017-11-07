package com.qs.image_packager.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

@Component("preparedStatementSetter")
public class ImageDownloadJobPreparedStatementSetter implements
		PreparedStatementSetter {

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, "N");
	}

}
