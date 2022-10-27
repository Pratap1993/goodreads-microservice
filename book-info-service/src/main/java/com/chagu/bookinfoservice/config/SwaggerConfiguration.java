package com.chagu.bookinfoservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.chagu.bookinfoservice")).paths(PathSelectors.any()).build()
                .apiInfo(apiInformation());
    }

    private ApiInfo apiInformation() {
        return new ApiInfo("Another Good-Reads", "A replica of good-reads(https://www.goodreads.com/)",
                "1.0.0", "Free To User",
                new Contact("Pratap Bhanu's Github", "https://github.com/Pratap1993/", "dhal.pratapbhanu@gmail.com"),
                "API License", "www.chagu.com", Collections.emptyList());
    }
}
