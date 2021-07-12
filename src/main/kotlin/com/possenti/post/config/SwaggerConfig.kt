package com.possenti.post.config


import com.google.common.base.Predicates.or

import springfox.documentation.builders.PathSelectors.regex

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import com.google.common.base.Predicate

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun postsApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private fun postPaths(): Predicate<String> {
        return or(regex("/post/.*"), regex("/posts.*"), regex("/reactions.*"));
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("POST microservice")
                .description("Posts API reference for developers")
                .termsOfServiceUrl("localhost:8081")
                .contact("rafael_possenti@hotmail.com").license("Smart License")
                .licenseUrl("rafael_possenti@hotmail.com").version("1.0").build();
    }


}