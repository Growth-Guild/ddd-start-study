package edu.study.order.domain;

import java.util.Objects;

public class OrderLine {
    private Product product;
    private Money price;
    private int quantity;

    public OrderLine() {
    }

    public OrderLine(Product product, int price, int quantity) {
        verifyQuantityIsMoreThanOne(quantity);
        this.product = product;
        this.price = new Money(price);
        this.quantity = quantity;
    }

    private void verifyQuantityIsMoreThanOne(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("최소 수량은 1개 이상입니다.");
        }
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getAmounts() {
        return price.multiply(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return quantity == orderLine.quantity && Objects.equals(product, orderLine.product) && Objects.equals(price, orderLine.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price, quantity);
    }
}
