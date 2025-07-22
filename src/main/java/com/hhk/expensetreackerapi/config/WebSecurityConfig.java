package com.hhk.expensetreackerapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hhk.expensetreackerapi.security.CustomUserDetailsService;
import com.hhk.expensetreackerapi.security.JwtRequestFilter;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	public JwtRequestFilter jwtRequestFilter;
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//			http
//				.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/login", "/register").permitAll()
//						.anyRequest().authenticated()
//				)
//				.sessionManagement(session -> session
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				)
//				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//				.httpBasic(Customizer.withDefaults());		
//
//		return http.build();
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
					auth -> auth.requestMatchers("/login", "/register")
					.permitAll()
					.anyRequest()
					.authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults())
				.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

//	@Bean
//	public AuthenticationManager authenticationManager() {
//		return new ProviderManager(List.of(authenticationProvider()));
//	}
}