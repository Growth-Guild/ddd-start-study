package edu.study.order.domain;

public class Order {
    private ShippingInfo shippingInfo;
    private OrderState orderState;

    public Order() {}

    public Order(ShippingInfo shippingInfo, OrderState orderState) {
        this.shippingInfo = shippingInfo;
        this.orderState = orderState;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public OrderState getOrderState() {
        return orderState;
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
}
