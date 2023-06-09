package com.nrx.basicauth.config;

import com.nrx.basicauth.dto.ModuleDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebSecurityConfig {

    private final AppProperties appProperties;
    private static final String[] PERMIT_LIST = {
        "/i18n/**",
        "/assets/**",
        "/webjars/**",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/actuator/**",
        "/api-docs/**"
    };

    public WebSecurityConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(
            authorize -> authorize.requestMatchers(HttpMethod.GET, PERMIT_LIST).permitAll().anyRequest()
                .authenticated())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable()).build();
    }

    @Bean
    public UserDetailsService users() {
        List<UserDetails> userDetails = new ArrayList<>();
        if(appProperties.myAppProperties()!=null){
            for(ModuleDto module : appProperties.myAppProperties().getCredentials()){
                UserDetails user = User.builder()
                        .username(module.getModule())
                        .password(encoder().encode(module.getPassword()))
                        .build();
                userDetails.add(user);
            }
        }
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(users());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }




}
