package com.cpd.coronapreventiondivision;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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