package com.poly.Yasuki.security;

import com.poly.Yasuki.handler.CustomAuthenticationSuccessHandler;
import com.poly.Yasuki.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder passwordEncoder;
    private final MyUserService userService;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

        .authorizeRequests()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/login", "/").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/order/**", "/cart/**").hasAnyAuthority("admin", "user")
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/");
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
