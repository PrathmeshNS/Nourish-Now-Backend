package com.pscode.nourish_now.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import com.pscode.nourish_now.filter.JwtFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtFilter filter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
 		RequestCache nullRequestCache= new NullRequestCache();
		return http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("*/login", "*/register","/donation/**").permitAll() // Allow public
						.requestMatchers("admin/**").hasAuthority("ROLE_ADMIN") // Admin access only
						.requestMatchers("ngo/**").hasAuthority("ROLE_NGO")  // NGO access only
						.requestMatchers("hotel/**").hasAuthority("ROLE_HOTEL")  // Hotel access only
						.requestMatchers("history/**").hasAnyAuthority("ROLE_ADMIN","ROLE_NGO","ROLE_HOTEL")
						.requestMatchers("available-food/add-available-food").hasAuthority("ROLE_HOTEL")
						.requestMatchers("available-food/**").hasAnyAuthority("ROLE_ADMIN","ROLE_NGO","ROLE_HOTEL")
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()) // Enable basic auth
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.requestCache(cache -> cache.requestCache(nullRequestCache))
				.logout(LogoutConfigurer::permitAll) // Enable logout
				.build();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(12);
	}

}
