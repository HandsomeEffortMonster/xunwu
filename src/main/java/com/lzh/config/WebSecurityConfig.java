package com.lzh.config;

import com.lzh.Security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lzh
 * @date 2018/12/24 10:50
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /***
     * http权限控制
     * **/
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //资源访问权限
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()//管理员登录入口
                .antMatchers("static/**").permitAll()//静态资源
                .antMatchers("/user/login").permitAll()//用户登录入口
                .antMatchers("admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")//配置角色的登录入口处理
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and();

        //关闭防御策略
        http.csrf().disable();
        //同源策略
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     * 只能注入一个AuthenticationManagerBuilder
     * **/
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //上面是通过内存认证的不需要
        /*auth.inMemoryAuthentication().withUser("admin").password("admin")
                .roles("ADMIN").and();*/
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Bean
    public AuthProvider authProvider(){
        return new AuthProvider();
    }
}
