package com.livelyit.allcam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//	}
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.headers().frameOptions().disable()		//권한 처리 부분 아직 미흡하여 제거
//			.and()
//			.authorizeRequests()
//			.anyRequest()
//			.authenticated();
//		if(!Utils.Debug) {
//			http.authorizeRequests().antMatchers("/**").hasRole("client_credentials");	//롤에 따라 접근 권한
//		}else {
//			http.csrf().disable();
//		}
    }
//		
//	@Override
//	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().antMatchers("/emailAuth");	//권한 없이 페이지 열기
//	}	
}