package com.geekster.ExpenseTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;
    @NotNull
    @Column(name = "product_title")
    private String productTitle;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_price")
    private Integer productPrice;
    @NotNull
    @Column(name = "local_date")
    private LocalDate localDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product(String userNotLoggedIn) {
        productTitle = userNotLoggedIn;
    }
}
