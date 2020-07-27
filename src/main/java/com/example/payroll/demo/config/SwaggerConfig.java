package com.example.payroll.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.payroll.demo")) //diretório raiz da aplicação
                //.paths(PathSelectors.any())
                .paths(regex("/employees.*")) //uri raiz da aplicação
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        // informações que terão na API
        ApiInfo apiInfo = new ApiInfo(
                "Employees API REST",
                "Application Rest API with Spring Boot",
                "1.0",
                "Terms of Service",
                /*new Contact("Júlia Vaz", "https://github.com/juliavaz/",
                        "juliavaz.dev@gmail.com"),*/
                this.contato(),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }

    private Contact contato() {
        return new Contact(
                "Júlia Vaz",
                "https://github.com/juliavaz/",
                "juliavaz.dev@gmail.com");
    }
}