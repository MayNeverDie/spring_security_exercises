package ru.esphere.school.controllers.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Запрос тестового контроллера")
public class TestRequest {
    @ApiModelProperty("Сообщение")
    private String message;
}
