package edu.study.order.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    @Test
    void 주문은_출고전에_배송지를_변경할_수_있다() {
        // given
        ShippingInfo givenShippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.PREPARING;
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(null, 1000, 5));

        ShippingInfo changedShippingInfo = new ShippingInfo("222222", "대전", "코틀린", "01056785678");

        Order order = new Order(givenShippingInfo, currentOrderState, orderLines);

        // when
        order.changeShippingInfo(changedShippingInfo);

        // then
        assertThat(order.getShippingInfo()).isEqualTo(changedShippingInfo);
    }

    @Test
    void 주문은_출고되면_배송지를_변경할_수_없다() {
        // given
        ShippingInfo beforeShippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");

        ShippingInfo changeShippingInfo = new ShippingInfo("222222", "대전", "자바", "01056785678");
        OrderState currentOrderState = OrderState.DELIVERING;
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(null, 1000, 5));

        Order order = new Order(beforeShippingInfo, currentOrderState, orderLines);

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
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(null, 1000, 5));

        Order order = new Order(shippingInfo, currentOrderState, orderLines);

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
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(null, 1000, 5));

        Order order = new Order(shippingInfo, currentOrderState, orderLines);

        // when
        assertThrows(
                IllegalArgumentException.class,
                order::cancel
        );

        // then
        assertThat(order.getOrderState()).isEqualTo(OrderState.DELIVERING);
    }

    @Test
    void 주문은_최소_한_종류_이상의_상품을_주문해야_한다() {
        // given
        ShippingInfo shippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.PAYMENT_WAITING;
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(null, 1000, 5));

        // when
        Order order = new Order(shippingInfo, currentOrderState, orderLines);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Order(shippingInfo, currentOrderState, Collections.emptyList()));
        assertThat(order.getOrderLines().size()).isGreaterThan(0);
    }

    @Test
    void 총_주문_금액은_각_상품의_구매_가격을_모두_더한_값이다() {
        // given
        List<OrderLine> orderLines = Arrays.asList(
                new OrderLine(new Product(), 1000, 5),
                new OrderLine(new Product(), 500, 3),
                new OrderLine(new Product(), 300, 10)
        );
        ShippingInfo shippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.PAYMENT_WAITING;
        int expectedTotalAmounts = orderLines.stream().mapToInt(v -> v.getAmounts().getValue()).sum();

        // when
        Order order = new Order(shippingInfo, currentOrderState, orderLines);

        // then
        assertThat(order.getTotalAmounts()).isEqualTo(expectedTotalAmounts);
    }
}
