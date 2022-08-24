package kr.or.lx.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.lx.common.ApiService;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("login")
@Slf4j
@Controller
public class LoginController {
	
    @Value("${agent.api.url}")
    private String agentApiUrl;    
	
    @Autowired
	private ApiService<?> apiService;
	
	/**
     * 로그인 화면
     * @return
     */	
	@GetMapping("/loginForm")
	public String loginForm(ModelMap model) throws Exception{
		
		return "login/login";
	}
	
//	/**
//     * 로그인 화면
//     * @return
//     */	
//	@GetMapping("/logout")
//	public String logout(ModelMap model) throws Exception{
//		
//		return "login/login";
//	}
	
}
