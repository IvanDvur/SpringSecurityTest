package com.example.httpbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .authorities("ACCESS_TEST1", "ACCESS_TEST2","ROLE_ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("ivan")
                .password("ivan123")
                .roles("USER")
                .build();
        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("manager123")
                .authorities("ACCESS_TEST1","ROLE_MANAGER")
                .build();
        return new InMemoryUserDetailsManager(admin, user, manager);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .antMatchers("/index.html").permitAll()
                        .antMatchers("/profile/**").authenticated()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                        .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                        .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                        .antMatchers("/api/public/users").hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
