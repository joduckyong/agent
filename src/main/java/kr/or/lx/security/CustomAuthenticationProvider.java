package kr.or.lx.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import kr.or.lx.common.ApiService;
import lombok.RequiredArgsConstructor;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Value("${agent.api.url}")
    private String agentApiUrl;    
	
    @Autowired
	private ApiService<?> apiService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        //입력한 ID, Password 조회
        String userId = token.getName();
        String userPw = (String)token.getCredentials();
        
        String url = agentApiUrl+"/user/login";
		Map<String, Object> param = new HashMap<>();
		param.put("user_id", userId);
		param.put("user_pwd", userPw);
		ResponseEntity<?> responseEntity = apiService.post(url, param);
		HttpStatus httpStatus = responseEntity.getStatusCode();
		if(httpStatus.is2xxSuccessful()) {
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
	        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, userPw, roles);
	        
	        UserDetail userDetail= new UserDetail();
	        userDetail.setUserNm(userId);
	        userDetail.setUserPw(userPw);

	        result.setDetails(userDetail);
	        
	        return result;
		}else {
			return null;
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
