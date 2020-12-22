package com.geekbrains.frontend;

import com.geekbrains.connectors.SmtpConnector;
import com.geekbrains.entities.Order;
import com.geekbrains.services.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Route("other-orders")
public class OtherOrderView extends OrderView {

    protected Select<String> statusSelect;
    private SmtpConnector smtpConnector;

    public OtherOrderView(OrderService orderService, SmtpConnector smtpConnector) {
        super(orderService);
        this.smtpConnector = smtpConnector;
    }

    @Override
    public void initOrderView() {
        super.initOrderView();
        orderGrid.setItems(orderService.findAll());
        orderGrid.removeColumnByKey("status");
        orderGrid.addColumn(new ComponentRenderer<>(item -> {
            statusSelect = new Select<>();
            statusSelect.setItems(List.of("MANAGING", "DELIVERING", "DELIVERED"));

            List<Order.Status> statuses = orderGrid.getDataProvider().fetch(new Query<>()).map(Order::getStatus).collect(Collectors.toList());
            Optional<Order.Status> currentStatus = statuses.stream().filter(i -> i.name().equals(item.getStatus())).findFirst();
            statusSelect.setValue(currentStatus.map(Enum::name).orElse(null));
            return statusSelect;
        }));
        Button saveOrders = new Button("Сохранить статус", a -> {
            List<Order> orders = orderGrid.getDataProvider().fetch(new Query<>())
                    .peek(o -> o.setStatus(Order.Status.valueOf(statusSelect.getValue())))
                    .collect(Collectors.toList());
            orderService.saveAll(orders);

            if (statusSelect.getValue().equals("DELIVERING")){
                smtpConnector.sendEmail();
            }

            Notification.show("Заказы успешно сохранены");
        });
        add(saveOrders);
    }
}
