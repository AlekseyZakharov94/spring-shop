package com.geekbrains.frontend;

import com.geekbrains.entities.Order;
import com.geekbrains.security.CustomPrincipal;
import com.geekbrains.security.SecurityUtils;
import com.geekbrains.services.OrderService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

import java.util.List;



public class OrderView extends AbstractView {
    
    protected final OrderService orderService;
    private final CustomPrincipal principal;
    protected Grid<Order> orderGrid;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        this.principal = SecurityUtils.getPrincipal();
        initOrderView();
    }

    protected void initOrderView() {
        orderGrid = new Grid<>(Order.class);
        List<Order> order = orderService.getByUserNAme(principal.getUsername());
        orderGrid.setItems(order);
        orderGrid.setColumns("address", "items", "phoneNumber", "price", "status");

        Button backButton = new Button("Назад", a -> UI.getCurrent().navigate("market"));

        add(orderGrid, backButton);
    }
}
