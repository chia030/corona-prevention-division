package com.cpd.coronapreventiondivision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    //This allows only specific pages to be accessed without logging in
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/");
        web.ignoring().antMatchers("/test");
        web.ignoring().antMatchers("/vaccine");
        web.ignoring().antMatchers("/email-verification");
        web.ignoring().antMatchers("/confirm-booking");
        web.ignoring().antMatchers("/get-google-maps-link");
        web.ignoring().antMatchers("/get-available-times");
        web.ignoring().antMatchers("/get-available-days");
        web.ignoring().antMatchers("/get-center-appointments");
        web.ignoring().antMatchers("/update-appointment-report");
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM cpd1.users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, level FROM users WHERE username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/create-center").hasAuthority("ADMIN")
                .antMatchers("/update-center").hasAuthority("ADMIN")
                .antMatchers("/submit-center").hasAuthority("ADMIN")
                .antMatchers("/secretary").hasAuthority("SECRETARY")
                .antMatchers("/get-center-appointments").hasAuthority("SECRETARY")
                .antMatchers("/update-appointment-report").hasAuthority("SECRETARY")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/index.html");
    }

    //Disabling some security measures to allow async POST calls from javascript to java
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
