package org.feego.oauth2.resources.dao;

import java.util.HashMap;
import java.util.Map;

import org.feego.oauth2.resources.repo.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoDao {
	@Autowired
	private ItemsMapper itemsMapper;
	
	public Map<String,Object> getItemById(Long id){
		Map<String,Object> param=new HashMap<>();
		param.put("id", id);
		Map<String,Object> item=itemsMapper.getById(param);
		return item;
	}
}
