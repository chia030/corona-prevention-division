package com.cpd.coronapreventiondivision;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
//                .antMatchers("")
                .anyRequest()//allow all urls
//                .authenticated()//all URLs are allowed by any authenticated user, no role restrictions.
//                .and()
//                .formLogin()//enable form based authentication
//                .loginPage("/my-login")//use a custom login URI
//                .permitAll(true)//login URI can be accessed by anyone
//                .and()
//                .logout()//default logout handling
//                .logoutSuccessUrl("/my-login?logout")//our new logout success url, we are not replacing other defaults.
                .permitAll();//allow all as it will be accessed when user is not logged in anymore
    }

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

//    @Override
//    public void configure(AuthenticationManagerBuilder builder)
//            throws Exception {
//        builder.inMemoryAuthentication()
//                .withUser("joe")
//                .password("123")
//                .roles("ADMIN");
//    }

//    @Bean
//    WebMvcConfigurer myWebMvcConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                ViewControllerRegistration r = registry.addViewController("/my-login");
//                r.setViewName("my-login-page");
//            }
//        };
//    }

}
