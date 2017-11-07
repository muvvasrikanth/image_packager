package com.qs.image_packager;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.qs.image_packager.domain.ImageDownloadJob;

@Component("reader")
public class ImagePackagerReader implements ItemReader<ImageDownloadJob> {

	@Override
	public ImageDownloadJob read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
