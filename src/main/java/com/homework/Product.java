package com.homework;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final Long id;
    private final String name;
    private final String category;
    private final BigDecimal price;


    public Product(Long id, String name, String category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean priceMoreThenValue(int T) {
        return getPrice().compareTo(new BigDecimal(T)) >0;
    }

    public BigDecimal getPriceWithDiscount(int discount) {
        return price.subtract(price.multiply(new BigDecimal(discount)).divide(new BigDecimal(100), RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
