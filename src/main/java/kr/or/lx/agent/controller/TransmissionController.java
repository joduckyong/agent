package kr.or.lx.agent.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.lx.common.ApiService;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("agent/transmission")
@Slf4j
@Controller
public class TransmissionController {
	
	@Value("${agent.api.url}")
    private String agentApiUrl;    
	
    @Autowired
	private ApiService<?> apiService;
	
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
	
	/**
     * 데이터 전송 관리 API
     * @return
     */		
	@ResponseBody
	@PostMapping("{apiId}")
	public Object transmissionPost(@RequestBody Map<String, Object> param, ModelMap model) throws Exception{
		log.info("transmissionPost");
		
		String url = agentApiUrl+param.get("url");
		
		ResponseEntity<?> responseEntity = apiService.post(url, param);
		Object object = responseEntity.getBody();
		
		if(object == null) {
			object = responseEntity.getStatusCode();
		}
		
		return object;
	}
	
}
