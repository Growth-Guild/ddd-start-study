package edu.study.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderLineTest {

    @Test
    void 상품은_최소_하나_이상_구매할_수_있다() {
        // given
        int price = 100;
        int quantity = 1;

        // when
        OrderLine orderLine = new OrderLine(new Product(), price, quantity);

        // then
        assertThat(orderLine.getPrice()).isEqualTo(price);
        assertThat(orderLine.getQuantity()).isEqualTo(quantity);
        assertThrows(IllegalArgumentException.class, () -> new OrderLine(new Product(), 1000, 0));
    }

    @Test
    void 주문상품의_가격은_수량과_곱한_값과_같다() {
        // given
        int price = 500;
        int quantity = 5;
        OrderLine orderLine = new OrderLine(new Product(), price, quantity);

        // when
        int amounts = orderLine.getAmounts();

        // then
        assertThat(amounts).isEqualTo(price * quantity);
    }
}
