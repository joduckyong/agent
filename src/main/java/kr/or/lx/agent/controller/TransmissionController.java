package kr.or.lx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("agent/transmission")
@Slf4j
@Controller
public class TransmissionController {
	
	@GetMapping("/list")
	public String transmissionList(ModelMap model) throws Exception{
		log.info("transmissionList");
		
		return "agent/transmission/list";
	}
	
	@GetMapping("/history")
	public String transmissionHistory(ModelMap model) throws Exception{
		log.info("transmissionHistory");
		
		return "agent/transmission/history";
	}
	
	@GetMapping("/add")
	public String transmissionAdd(ModelMap model) throws Exception{
		log.info("transmissionAdd");
		
		return "agent/transmission/add";
	}
	
	@GetMapping("/log")
	public String transmissionLog(ModelMap model) throws Exception{
		log.info("transmissionLog");
		
		return "agent/transmission/log";
	}	
	
}
