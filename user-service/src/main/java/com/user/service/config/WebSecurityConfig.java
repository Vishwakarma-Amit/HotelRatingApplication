package com.user.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Bean
	  public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity
                .authorizeHttpRequests(auth-> auth.requestMatchers("/**").authenticated());
        
         httpSecurity.oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults())
                	);
            
					
		 return httpSecurity.build();
                
	  }

}
