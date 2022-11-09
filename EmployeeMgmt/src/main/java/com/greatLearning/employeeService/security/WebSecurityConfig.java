package com.greatLearning.employeeService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("unused")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("kiran")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("vinay")
                .password(passwordEncoder().encode("admin"))
                .roles("USER","ADMIN");

	}
	
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.cors().disable();
	        httpSecurity.csrf().disable();
	        httpSecurity
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET,"/api/employees/**")
	                .hasAnyRole("USER", "ADMIN")
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST,"/api/employees/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.PUT,"/api/employees/{employeeId}**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.DELETE,"/api/employees/{employeeId}/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.GET,"/api/employees/search/{firstName}/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.GET,"/api/employees/sortAsc/**")
	                .hasAnyRole("USER","ADMIN")
	                .antMatchers(HttpMethod.GET,"/api/employees/sortDesc/**")
	                .hasAnyRole("USER","ADMIN")
	                .antMatchers(HttpMethod.POST,"/api/role/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.POST,"/api/user/**")
	                .hasRole("ADMIN")
	                .anyRequest()
	                .authenticated()
	                .and()
	                .formLogin()
	                .and()
	                .httpBasic()
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  
	    }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

}