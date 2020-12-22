package com.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "price_history")
@Data
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String dateOfChange;

    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_price_history",
    joinColumns = @JoinColumn(name = "price_history_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
