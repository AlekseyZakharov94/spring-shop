package com.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// TODO:
// 1. Отношение цены к товару. ManyToMany? Json?
//2. Добавить обработку NotFoundException в ExceptionHandlerImpl
//3. Добавить в com.geekbrains.services.UserService.getAllUsersWithType обработку которая маппит UserType в Role.
//Например: UserType.MANAGER = ROLE_MANAGER
//4. Добавить метод в UserRepository, который ищет по роли.
