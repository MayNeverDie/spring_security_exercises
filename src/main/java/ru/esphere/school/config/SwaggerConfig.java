package ru.esphere.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "ru.esphere.school";
    private static final Contact CONTACT = new Contact("КОРУС Консалтинг СНГ", "https://www.esphere.ru", "info@esphere.ru");

    @Bean
    @Profile("prod")
    public Docket apiControllers(Environment env) {
        final Set<String> schemes = new HashSet<>(2);
        schemes.add("http");
        schemes.add("https");

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .useDefaultResponseMessages(false)
                .protocols(schemes)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.regex("/api/.*"))
                .build().apiInfo(buildApiInfo(env));
    }

    @Bean
    @Profile("!prod")
    public Docket allControllers(Environment env) {
        final Set<String> schemes = new HashSet<>(2);
        schemes.add("http");
        schemes.add("https");

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("all")
                .useDefaultResponseMessages(false)
                .protocols(schemes)
                .apiInfo(buildApiInfo(env))
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    private ApiInfo buildApiInfo(Environment env) {
        return new ApiInfo(
                env.getProperty("spring.application.name", "pc-configurator-backend"),
                "pc-configurator-backend",
                getClass().getPackage().getImplementationVersion(),
                null,
                CONTACT,
                null,
                null,
                Collections.emptyList());
    }
}
