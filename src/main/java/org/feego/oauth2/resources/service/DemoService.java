package org.feego.oauth2.resources.service;

import java.util.Map;

import org.feego.oauth2.resources.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	@Autowired
	private DemoDao dao;
	
	public Map<String,Object> getItem(Long id){
		Map<String,Object> item=dao.getItemById(id);
		return item;
	}
}
