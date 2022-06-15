package kr.or.lx.agent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("agent/home")
@Slf4j
@Controller
public class HomeController {
	
	@GetMapping
	public String agentHome(ModelMap model) throws Exception{
		log.info("agentHome");
		
		return "agent/home";
	}
	
	
}
