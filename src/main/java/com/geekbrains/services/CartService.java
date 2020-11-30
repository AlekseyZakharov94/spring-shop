package com.geekbrains.services;

import com.geekbrains.entities.OrderItem;
import com.geekbrains.entities.Product;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<OrderItem> items;
    private BigDecimal price;
    private String address;
    private String phone;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void add(Product product) {
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increment();
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(product));
        recalculate();
    }

    public void recalculate() {
        price = new BigDecimal(0);
        items.stream().forEach(item ->
                price = price.add(item.getPrice())
        );
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
