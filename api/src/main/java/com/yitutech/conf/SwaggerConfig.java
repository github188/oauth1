package com.yitutech.conf;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhijun.li
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yitutech.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
 
    }
 
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Yitu Backend Example REST API",
                "Just an example to guide you how to use Swagger",
                "1.0",
                "Example of Restful API",
                new Contact("Zhaorong.Tang", "", "zhaorong.tang@yitu-inc.com"),
                "MIT",
                "");
        return apiInfo;
    }
}