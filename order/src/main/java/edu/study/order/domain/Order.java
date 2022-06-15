package edu.study.order.domain;

import java.util.List;
import java.util.Objects;

public class Order {
    private String orderNumber;
    private ShippingInfo shippingInfo;
    private OrderState orderState;
    private List<OrderLine> orderLines;
    private int totalAmounts;

    public Order() {
    }

    public Order(ShippingInfo shippingInfo, OrderState orderState, List<OrderLine> orderLines) {
        verifyOrderLineIsMoreThanOne(orderLines);
        this.shippingInfo = shippingInfo;
        this.orderState = orderState;
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    public Order(String orderNumber, ShippingInfo shippingInfo, OrderState orderState, List<OrderLine> orderLines) {
        this(shippingInfo, orderState, orderLines);
        this.orderNumber = orderNumber;
    }

    private void verifyOrderLineIsMoreThanOne(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("주문 항목은 최소 하나여야 합니다.");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = orderLines.stream().mapToInt(v -> v.getAmounts().getValue()).sum();
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        if (orderState.isAfterDelivered()) {
            throw new IllegalArgumentException("이미 배송되어 배송지 변경이 불가능합니다.");
        }
        this.shippingInfo = shippingInfo;
    }

    public void cancel() {
        if (orderState.isAfterDelivered()) {
            throw new IllegalArgumentException("배송된 이후에는 주문을 취소할 수 없습니다.");
        }
        orderState = OrderState.CANCELED;
    }

    public int getTotalAmounts() {
        return totalAmounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }
}
