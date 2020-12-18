package com.geekbrains.frontend;

import com.geekbrains.repositories.UserRepository;
import com.geekbrains.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("registration")
@PageTitle("Registration | Vaadin Show")
public class RegistrationView extends AbstractView {

    private final UserService userService;

    public RegistrationView(UserService userService) {
        this.userService = userService;
        initRegistrationView();
    }

    private void initRegistrationView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        TextField phoneTextField = initTextFieldWithPlaceholder("Телефон");
        TextField passwordTextField = initTextFieldWithPlaceholder("Пароль");
        TextField firstNameTextField = initTextFieldWithPlaceholder("Имя");
        TextField lastNameTextField = initTextFieldWithPlaceholder("Фамилия");
        TextField emailTextField = initTextFieldWithPlaceholder("Email");
        TextField ageTextField = initTextFieldWithPlaceholder("Возраст");

        Button registrationButton = new Button("Зарегистрироваться", a -> {
            boolean hasError = false;

            if (!phoneTextField.getValue().matches("\\d+")) {
                Notification.show("Телефон должен состоять из цифр");
                phoneTextField.setInvalid(true);
                hasError = true;
            }

            if (!firstNameTextField.getValue().matches("[a-zA-Zа-яА-Я]+")) {
                Notification.show("Имя должно состоять из букв");
                phoneTextField.setInvalid(true);
                hasError = true;
            }

            if (!lastNameTextField.getValue().matches("[a-zA-Zа-яА-Я]+")) {
                Notification.show("Фамилия должна состоять из букв");
                phoneTextField.setInvalid(true);
                hasError = true;
            }

            if (!ageTextField.getValue().matches("\\d+") || Integer.valueOf(ageTextField.getValue()) < 18) {
                Notification.show("Возраст должен состоять из цифр и вы должны быть старше 18 лет");
                phoneTextField.setInvalid(true);
                hasError = true;
            }

            if (hasError) {
                return;
            } else {
                userService.saveUser(phoneTextField.getValue(), passwordTextField.getValue(), firstNameTextField.getValue(),
                        lastNameTextField.getValue(), emailTextField.getValue(), ageTextField.getValue());
                UI.getCurrent().navigate("login");
            }
        });

        add(phoneTextField, passwordTextField, firstNameTextField, lastNameTextField, emailTextField,
                ageTextField, registrationButton);
    }
}
