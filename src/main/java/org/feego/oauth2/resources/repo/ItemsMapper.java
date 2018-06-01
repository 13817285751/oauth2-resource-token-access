package org.feego.oauth2.resources.repo;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemsMapper {
	public Map<String,Object> getById(Map<String,Object> param);
}
