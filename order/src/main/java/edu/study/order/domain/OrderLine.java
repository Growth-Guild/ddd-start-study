package edu.study.order.domain;

public class OrderLine {
    private Product product;
    private int price;
    private int quantity;

    public OrderLine() {
    }

    public OrderLine(Product product, int price, int quantity) {
        verifyQuantityIsMoreThanOne(quantity);
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    private void verifyQuantityIsMoreThanOne(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("최소 수량은 1개 이상입니다.");
        }
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmounts() {
        return price * quantity;
    }
}
