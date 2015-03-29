package com.adjectivecolournoun.contenttype.server

import com.adjectivecolournoun.contenttype.server.domain.ThingOne
import com.adjectivecolournoun.contenttype.server.domain.ThingThree
import com.adjectivecolournoun.contenttype.server.domain.ThingTwo
import com.adjectivecolournoun.contenttype.server.repository.ThingRepository
import com.mangofactory.swagger.configuration.SpringSwaggerConfig
import com.mangofactory.swagger.models.dto.ApiInfo
import com.mangofactory.swagger.plugin.EnableSwagger
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSwagger
@Slf4j
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

    @Bean
    CommandLineRunner createThings(ThingRepository thingRepository) {
        return {
            def thingOne = thingRepository.save(new ThingOne(name: 'first thing'))
            log.info "Created first thing with id $thingOne.id"
            def thingTwo = thingRepository.save(new ThingTwo(name: 'second thing', type: 'a thing'))
            log.info "Created second thing with id $thingTwo.id"
            def thingThree = thingRepository.save(new ThingThree(name: 'third thing', type: 'a thing', count: 1))
            log.info "Created third thing with id $thingThree.id"
        } as CommandLineRunner
    }
}
