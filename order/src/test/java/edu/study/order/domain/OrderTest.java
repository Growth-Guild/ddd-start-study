package edu.study.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    @Test
    void 주문은_출고전에_배송지를_변경할_수_있다() {
        // given
        ShippingInfo shippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.PREPARING;
        Order order = new Order(shippingInfo, currentOrderState);

        // when
        order.changeShippingInfo(shippingInfo);

        // then
        assertThat(order.getShippingInfo()).isEqualTo(shippingInfo);
    }

    @Test
    void 주문은_출고되면_배송지를_변경할_수_없다() {
        // given
        ShippingInfo beforeShippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");

        ShippingInfo changeShippingInfo = new ShippingInfo("222222", "대전", "자바", "01056785678");
        OrderState currentOrderState = OrderState.DELIVERING;
        Order order = new Order(beforeShippingInfo, currentOrderState);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> order.changeShippingInfo(changeShippingInfo)
        );

        // then
        assertThat(order.getShippingInfo()).isEqualTo(beforeShippingInfo);
    }

    @Test
    void 주문은_출고전에_취소할_수_있다() {
        // given
        ShippingInfo shippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.PREPARING;
        Order order = new Order(shippingInfo, currentOrderState);

        // when
        order.cancel();

        // then
        assertThat(order.getOrderState()).isEqualTo(OrderState.CANCELED);
    }

    @Test
    void 주문은_출고된_이후에는_취소할_수_없다() {
        // given
        ShippingInfo shippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.DELIVERING;
        Order order = new Order(shippingInfo, currentOrderState);

        // when
        assertThrows(
                IllegalArgumentException.class,
                order::cancel
        );

        // then
        assertThat(order.getOrderState()).isEqualTo(OrderState.DELIVERING);
    }
}
