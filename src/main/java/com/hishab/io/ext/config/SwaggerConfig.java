package com.hishab.io.ext.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 * The type Swagger config.
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {
    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() { 
        return new Docket(SWAGGER_2)
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.hishab.io.ext"))
          .paths(PathSelectors.any())
          .build();                                           
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}