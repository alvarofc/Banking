package com.ironhack.banking.security;

import com.ironhack.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                .mvcMatchers("/accounts/**").hasAnyAuthority( "ADMIN")
                .mvcMatchers("/account/**").hasAnyAuthority("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST,"/account/**").hasAuthority( "ADMIN")
                .mvcMatchers(HttpMethod.POST,"/saving/**").hasAuthority( "ADMIN")
                .mvcMatchers(HttpMethod.POST,"/address/**").hasAuthority( "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/creditcard").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/creditcard/**").hasAnyAuthority("ADMIN", "THIRD_PARTY","USER")
                .mvcMatchers(HttpMethod.POST, "/third-party").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/transfer/**").hasAuthority("USER")
                .mvcMatchers( "/all-transactions").hasAuthority("ADMIN")
                .mvcMatchers("/user/**").hasAnyAuthority( "ADMIN")
                .anyRequest().permitAll();
        //.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(unauthorizedHandler);
        //httpSecurity.formLogin().failureHandler(new CustomAuthenticationFailureHandler());

    }
}
