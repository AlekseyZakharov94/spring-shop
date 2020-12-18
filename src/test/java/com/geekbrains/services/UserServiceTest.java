package com.geekbrains.services;

import com.geekbrains.Application;
import com.geekbrains.controllers.dto.RoleDto;
import com.geekbrains.controllers.dto.UserDto;
import com.geekbrains.entities.Role;
import com.geekbrains.entities.User;
import com.geekbrains.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {

    @MockBean(name = "userRepository")
    private UserRepository userRepository;

    @MockBean
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Test
    void saveUser() {
        Role role = new Role();
        UserDto manager = UserDto.builder()
                .phone("123")
                .age(20)
                .email("ajfhsaked")
                .firstName("zsger")
                .lastName("zrgse")
                .password("shefj")
                .roleDto(RoleDto.MANAGER)
                .build();
        User user = getUser(role, manager);
        when(roleService.getByName("ROLE_MANAGER")).thenReturn(role);
        User userWithId = getUserWithId(role, manager);
        doReturn(userWithId).when(userRepository).save(eq(user));
        User savedUser = userService.saveUser(manager);

        assertThat(savedUser)
                .isNotNull()
                .isInstanceOf(User.class)
                .isEqualTo(userWithId);
        verify(userRepository, times(1)).save(any());
    }

    private User getUser(Role role, UserDto manager) {
        User user = new User();
        user.setEmail(manager.getEmail());
        user.setFirstName(manager.getFirstName());
        user.setLastName(manager.getLastName());
        user.setPassword(manager.getPassword());
        user.setPhone(manager.getPhone());
        user.setAge(manager.getAge());
        user.setRoles(List.of(role));
        return user;
    }

    private User getUserWithId(Role role, UserDto manager) {
        User user = getUser(role, manager);
        user.setId(1L);
        return user;
    }
}