package com.geekbrains.frontend;

import com.geekbrains.entities.OrderItem;
import com.geekbrains.services.CartService;
import com.geekbrains.services.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("cart")
public class CartView extends AbstractView {

    private final CartService cartService;
    private final OrderService orderService;

    public CartView(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        initCartPage();
    }

    private void initCartPage() {
        Grid<OrderItem> grid = new Grid<>(OrderItem.class);
        grid.setItems(cartService.getItems());
        grid.setWidth("60%");
        grid.setColumns("product", "quantity", "price");
        grid.addColumn(new ComponentRenderer<>(item -> {
            Button plusButton = new Button("+", a -> {
                item.increment();
                grid.setItems(cartService.getItems());
            });
            Button minusButton = new Button("-", a -> {
                item.decrement();
                grid.setItems(cartService.getItems());
            });
            return new HorizontalLayout(plusButton, minusButton);
        }));
        TextField addressField = initTextFieldWithPlaceholder("Введите адрес доставки");
        TextField phoneField = initTextFieldWithPlaceholder("Введите телефон");
        Button orderButton = new Button("Создать заказ", a -> {
            cartService.setAddress(addressField.getValue());
            cartService.setPhone(phoneField.getValue());
            orderService.saveOrder();

            Notification.show("Заказ успешно сохранен и передан менеджеру");
        });

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(grid, addressField, phoneField, orderButton);
    }
}
