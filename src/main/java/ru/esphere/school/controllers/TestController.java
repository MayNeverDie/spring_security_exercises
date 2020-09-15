package ru.esphere.school.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.esphere.school.controllers.dto.TestRequest;
import ru.esphere.school.controllers.dto.TestResponse;

import static ru.esphere.school.constants.RequestMapping.TEST;

@Slf4j
@Validated
@RestController
@RequestMapping(TEST)
@RequiredArgsConstructor
public class TestController {

    @Value("${spring.application.name}")
    private final String applicationName;

    @GetMapping
    @ApiOperation(value = "Проверка статуса сервиса")
    public TestResponse check(@RequestParam("request") TestRequest testRequest) {
        log.info("Handle request {}", testRequest);
        TestResponse testResponse = new TestResponse(
                String.format(
                        "Application '%s' is working! Request: '%s'.",
                        this.applicationName, testRequest));
        log.info("Handled request {}. Response: {}", testRequest, testResponse);
        return testResponse;
    }

}
