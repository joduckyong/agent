package kr.or.lx.agent.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.lx.common.ApiService;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("agent/admin")
@Slf4j
@Controller
public class AdminController {
	
    @Value("${agent.api.url}")
    private String agentApiUrl;	
   
	@Autowired
	private ApiService<?> apiService;
	
	@GetMapping("/list")
	public String adminList(ModelMap model) throws Exception{
		
		return "agent/admin/list";
	}	
	
	@GetMapping("/add")
	public String adminAdd(Map<String, Object> param, ModelMap model) throws Exception{
		
		model.put("clct_id", param.get("clct_id"));	
		
		return "agent/admin/add";
	}	
	
	@ResponseBody
	@PostMapping("{apiId}")
	public Object adminApi(@RequestBody Map<String, Object> param, ModelMap model) throws Exception{
		
		String url = agentApiUrl+param.get("url");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		param.put("user_id", String.valueOf(auth.getName()));
		
		ResponseEntity<?> responseEntity = apiService.post(url, param);
		Object object = responseEntity.getBody();
		
		return object;
	}	
	
	
}
