package com.proj.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select email as principal,password as credentials, active from `compte` where email=?")
		.authoritiesByUsernameQuery("select email as principal,role as role from `compte` where email=?")
		.passwordEncoder(getPasswordEncoder() )
		.rolePrefix("ROLE_")
		;
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/etudiant/*").hasRole("ETUDIANT");
		http.authorizeRequests().antMatchers("/professeur/*").hasRole("PROFESSEUR");
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		
		
		http.exceptionHandling().accessDeniedPage("/403");
	}

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	}

}
