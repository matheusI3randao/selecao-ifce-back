package com.selecao.ifce.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class ModelMapperUtils {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
	    return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
	} 
}
