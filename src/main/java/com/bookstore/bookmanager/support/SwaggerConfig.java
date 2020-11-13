package com.bookstore.bookmanager.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket( DocumentationType.SWAGGER_2 )
                       .select()
                       .apis( RequestHandlerSelectors.withClassAnnotation( RestController.class ) )
                       .paths( PathSelectors.any() )
                       .build()
                       .apiInfo( metaData() );
    }

    private ApiInfo metaData()
    {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        return apiInfoBuilder.title( "Package Search REST API" )
                       .version( "1.0" )
                       .description( "Mock API for Package Search" )
                       .build();
    }
}
