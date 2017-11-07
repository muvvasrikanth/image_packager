package com.qs.image_packager.util;

import java.util.List;

public class ParameterBuilder {

	public static String listToSqlParameter(List<? extends Object> list){
		StringBuilder builder = new StringBuilder() ;
		int index = 1 ;
		builder.append("'") ;
		for( Object o : list){
			builder.append(o.toString().trim()) ;
			builder.append(index < list.size() ? "', '" : "'") ;
			index++ ;
		}
		return builder.toString() ;
	}
}
