package com.geekbrains.frontend;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Vaadin Shop")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm loginForm = new LoginForm();

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.setAction("login");
        add(new H1("Vaadin Shop"), loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(event.getLocation().getQueryParameters().getParameters().containsKey("error")){
            loginForm.setError(true);
        }
    }
}

