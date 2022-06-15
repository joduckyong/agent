package kr.or.lx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("agent/collectionTb")
@Slf4j
@Controller
public class CollectionTbController {
	
	@GetMapping("/list")
	public String collectionTbList(ModelMap model) throws Exception{
		
		
		return "agent/collectionTb/list";
	}	
	
	@GetMapping("/add")
	public String collectionTbAdd(ModelMap model) throws Exception{
		
		
		return "agent/collectionTb/add";
	}	
	
	
}
