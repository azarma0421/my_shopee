package com.JamesCode.my_shopee.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    // 從DB核對登入資訊
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails john = User.builder()
//                .username("admin")
//                .password("{noop}admin")
//                .roles("EMPLOYEE")
//                .build();
//
//        return new InMemoryUserDetailsManager(john);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler())
                .loginPage("/LoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll();

        // 方便用postman測試api
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    private AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException {
                String username = authentication.getName(); // Get the username from the Authentication object
                request.getSession().setAttribute("username", username); // Store the username in the session
                getRedirectStrategy().sendRedirect(request, response, "/");
            }
        };
    }

}
