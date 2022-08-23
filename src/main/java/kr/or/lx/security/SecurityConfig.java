package kr.or.lx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/vendors/**");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/agent/**").authenticated()
            .antMatchers("/**").permitAll();

        http.formLogin()
	        .loginPage("/login/loginForm")
	        .loginProcessingUrl("/login/loginProc")
	        .defaultSuccessUrl("/")
	        .usernameParameter("username")
	        .passwordParameter("password")
	        .permitAll();
        
        http.logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
	        .logoutSuccessUrl("/login/loginForm")
	        .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }
}
