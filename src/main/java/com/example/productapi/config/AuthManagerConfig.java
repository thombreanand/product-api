package com.example.productapi.config;
import com.example.productapi.repository.UserRepository;
import org.springframework.context.annotation.*; import org.springframework.security.authentication.AuthenticationManager; import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; import org.springframework.security.core.userdetails.*;
@Configuration public class AuthManagerConfig {
 @Bean UserDetailsService userDetailsService(UserRepository repo){return username->repo.findByUsername(username).map(u->User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).build()).orElseThrow(()->new UsernameNotFoundException("User not found"));}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
}
