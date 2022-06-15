package kr.or.lx.agent.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.lx.common.ApiService;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("agent/collectionMethod")
@Slf4j
@Controller
public class CollectionMethodController {
	
    @Value("${agent.api.url}")
    private String agentApiUrl;    
	
    @Autowired
	private ApiService<?> apiService;
	
	/**
     * 데이터 수집 방식 관리 - 목록 화면
     * @return
     */	
	@GetMapping("/list")
	public String collectionMethodList(ModelMap model) throws Exception{
		
		model.addAttribute("method_cd", "");
		return "agent/collectionMethod/list";
	}
	
	/**
     * 데이터 수집 방식 관리 - 목록 화면
     * @return
     */	
	@PostMapping("/list")
	public String collectionMethodList(@RequestParam(value = "method_cd") String methodCd, ModelMap model) throws Exception{
		
//		model.addAttribute("method_cd", methodCd != null ? methodCd : "");
		model.addAttribute("method_cd", methodCd);
		return "agent/collectionMethod/list";
	}
	
	/**
     * 데이터 수집 방식 관리 - 등록 화면
     * @return
     */	
	@GetMapping("/add")
	public String collectionMethodAdd(ModelMap model) throws Exception{
		
		return "agent/collectionMethod/add";
	}
	
	/**
     * 데이터 수집 방식 관리 - 수정/삭제 화면
     * @return
     */
	@GetMapping("/update/{methodCd}/{updateId}")
	public String collectionMethodUpdate(@PathVariable String methodCd, @PathVariable String updateId, ModelMap model) throws Exception{
		
		model.addAttribute("method_cd", methodCd);
		model.addAttribute("update_id", updateId);
		return "agent/collectionMethod/update";
	}
	
	/**
     * 데이터 수집 방식 관리 - 데이터 수집 방식 API
     * @return
     */		
	@ResponseBody
	@PostMapping("{apiId}")
	public Object collectionMethodPost(@RequestBody Map<String, Object> param, ModelMap model) throws Exception{
		log.info("collectionMethodPost");
		
		String url = agentApiUrl+param.get("url");
		
		ResponseEntity<?> responseEntity = apiService.post(url, param);
		Object object = responseEntity.getBody();
		
		if(object == null) {
			object = responseEntity.getStatusCode();
		}
		
		return object;
	}
	
	/**
     * 데이터 수집 방식 관리 - 데이터 수집 방식 수정 API
     * @return
     */		
	@ResponseBody
	@PatchMapping("{apiId}")
	public Object collectionMethodPatch(@RequestBody Map<String, Object> param, ModelMap model) throws Exception{
		log.info("collectionMethodPatch");
		
		String url = agentApiUrl+param.get("url");
		
		ResponseEntity<?> responseEntity = apiService.patch(url, param);
		Object object = responseEntity.getBody();
		
		if(object == null) {
			object = responseEntity.getStatusCode();
		}
		
		return object;
	}
	
	/**
     * 데이터 수집 방식 관리 - 데이터 수집 방식 삭제 API
     * @return
     */		
	@ResponseBody
	@DeleteMapping("{apiId}")
	public Object collectionMethodDelete(@RequestBody Map<String, Object> param, ModelMap model) throws Exception{
		log.info("collectionMethodDelete");
		
		String url = agentApiUrl+param.get("url");
		
		ResponseEntity<?> responseEntity = apiService.delete(url, param);
		Object object = responseEntity.getBody();
		
		if(object == null) {
			object = responseEntity.getStatusCode();
		}
		
		return object;
	}
}
