package com.adjectivecolournoun.contenttype.server

import com.mangofactory.swagger.configuration.SpringSwaggerConfig
import com.mangofactory.swagger.models.dto.ApiInfo
import com.mangofactory.swagger.plugin.EnableSwagger
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSwagger
class Application {

    static void main(String... args) {
        SpringApplication.run Application, args
    }

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig

    @Bean
    SwaggerSpringMvcPlugin customImplementation() {
        new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*thing.*");
    }

    private ApiInfo apiInfo() {
        new ApiInfo(
                'Thing API',
                'An API to return Things',
                null,
                null,
                null,
                null
        )
    }
}
