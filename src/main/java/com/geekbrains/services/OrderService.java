package com.geekbrains.services;

import com.geekbrains.aspect.Log;
import com.geekbrains.entities.Order;
import com.geekbrains.entities.User;
import com.geekbrains.repositories.OrderItemRepository;
import com.geekbrains.repositories.OrderRepository;
import com.geekbrains.security.CustomPrincipal;
import com.geekbrains.security.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserService userService,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
    }

    @Log
    public void saveOrder() {
        User user = userService.findByUsername(SecurityUtils.getPrincipal().getUsername());

        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhone());
        order.setUser(user);
        order.setPrice(cartService.getPrice());
        order.setStatus(Order.Status.MANAGING);
        order.setPhoneNumber(user.getPhone());

        final Order savedOrder = orderRepository.save(order);
    }

    @Transactional
    public List<Order> getByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getByUserNAme(String username) { return orderRepository.findAllByUser_Phone(username);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void saveAll(List<Order> orders) {
        orderRepository.saveAll(orders);
    }
}
