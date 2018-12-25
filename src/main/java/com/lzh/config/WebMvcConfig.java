package com.lzh.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;


/**
 * 与web有关配置
 * @author lzh
 * @date 2018/12/22 20:49
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 静态资源加载类
     * **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 模板资源解析器
     * **/
    @Bean
    @ConfigurationProperties(prefix = "spring.thymeleaf")//这里前缀绑定后才能找到资源
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /**
     * Thymeleaf标准方言解释器
     * **/
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        //支持EL表达式
        templateEngine.setEnableSpringELCompiler(true);

        //支持springsecurity方言
        SpringSecurityDialect securityDialect = new SpringSecurityDialect();
        templateEngine.addDialect(securityDialect);
        return templateEngine;
    }

    /**
     * 视图解析器
     * **/
    @Bean
    public ThymeleafViewResolver viewResolver(){
        //一般jsp都拥护这个结尾的名字
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

}
