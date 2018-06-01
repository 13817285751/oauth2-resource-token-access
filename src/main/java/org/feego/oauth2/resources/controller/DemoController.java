package org.feego.oauth2.resources.controller;

import java.util.Map;

import org.feego.oauth2.resources.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api",method=RequestMethod.POST,produces="application/json;charset=utf-8")
public class DemoController {
	private final Logger logger=LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private DemoService svr;
	
	
	@RequestMapping(value="/item")
	public Map<String,Object> getItem(@RequestParam  Long id){
		Map<String,Object> resp=svr.getItem(id);
		return resp;
	}
}
