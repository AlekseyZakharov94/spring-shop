package com.geekbrains.controllers.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDto {

    @NotNull(message = "Телефон пользователя не может быть пустым.")
    private String phone;
    @NotNull(message = "Пароль не может быть пустым.")
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @NotNull(message = "Возраст пользователя должен быть не пустым.")
    private Integer age;
    @NotNull(message = "Тип пользователя не указан.")
    private RoleDto roleDto;
}
