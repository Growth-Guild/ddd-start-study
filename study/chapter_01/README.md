# Chapter 1. 도메인 모델 시작하기

## 1.1 도메인이란?
* 도메인은 소프트웨어로 해결하고자 하는 문제 영역이다.
* 하나의 도메인은 다시 하위 도메인으로 나눌 수 있다.
* 한 하위 도메인은 다른 하위 도메인과 연동하여 완전한 기능을 제공한다.
  * ex) 고객이 물건을 구매하면 주문, 결제, 배송, 혜택 하위 도메인의 기능이 엮이게 된다.
* 특정 도메인을 위한 소프트웨어라고 해서 도메인이 제공해야 할 모든 기능을 직접 구현하는 것은 아니다.
  * 배송, 결제 등은 외부 업체 시스템을 사용할 때가 많다.

## 1.2 도메인 전문가와 개발자 간 지식 공유
* 각 도메인 영역에는 전문가가 있다. 이들 전문가는 해당 도메인에 대한 지식과 경험을 바탕으로 원하는 기능 개발을 요구한다.
* 개발자는 이런 요구사항을 분석하고 설계하여 코드를 작성하며 테스트하고 배포한다.
* 요구사항을 올바르게 이해하지 못하면 요구하지 않은 엉뚱한 기능을 만들게 된다. 따라서 코딩에 앞서 요구사항을 올바르게 이해하는 것이 중요하다.

### 요구사항을 올바르게 이해하는 방법
* 가장 간단한 방법은 개발자와 전문가가 직접 대화하는 것이다.
* 개발자와 전문가 사이에 내용을 전파하는 전달자가 많으면 많을수록 정보가 왜곡되고 손실이 발생하게 된다.
* 도메인 전문가 만큼은 아니겠지만 이해관계자와 개발자도 도메인 지식을 갖추어야 한다.
* 도메인 전문가가 소프트웨어 전문가는 아니기 때문에 기존에 만들어진 소프트웨어를 기준으로 요구사항을 맞출 때가 있다.
  * 전문가나 관련자가 요구한 내용이 항상 올바른 것은 아니며 때론 본인들이 실제로 원하는 것을 정확하게 표현하지 못할 때도 있다.
  * 개발자는 요구사항을 이해할 때 왜 이런 기능을 요구하는지 또는 실제로 원하는 게 무엇인지 생각하고 전문가와 대화를 통해 진짜로 원하는 것을 찾아야 한다.

## 1.3 도메인 모델
* 도메인 모델에는 다양한 정의가 존재하는데, 기본적으로 도메인 모델은 특정 도메인을 개념적으로 표현한 것이다.
* 도메인 모델을 사용하면 여러 관계자들이 동일한 모습으로 도메인을 이해하고 도메인 지식을 공유하는 데 도움이 된다.
* 도메인을 이해하려면 도메인이 제공하는 기능과 도메인의 주요 데이터 구성을 파악해야 하는데, 이런 면에서 기능과 데이터를 함께 보여주는 객체 모델은 도메인을 모델링하기에 적합하다.
* 도메인 모델을 표현할 때 클래스 다이어그램이나 상태 다이어그램과 같은 UML 표기법만 사용해야 하는 것은 아니다.
* 도메인을 이해하는 데 도움이 된다면 표현 방식이 무엇인지는 중요하지 않다.

### 하위 도메인과 모델
* 각 하위 도메인이 다루는 영역은 서로 다르기 때문에 같은 용어라도 도메인마다 의미가 달라질 수 있다.
* 도메인에 따라 용어 의미가 결정되므로 여러 하위 도메인을 하나의 다이어그램에 모델링하면 안된다.
* 모델의 각 구성요소는 특정 도메인으로 한정할 때 비로소 의미가 완전해지기 때문에 각 하위 도메인마다 별도로 모델을 만들어야 한다.

## 1.4 도메인 모델 패턴
### 아키텍쳐 구성
* 일반적으로 애플리케이션의 아키텍처는 네 개의 영역으로 구성된다.
1. 표현 계층
   * 사용자의 요청을 처리하고 사용자에게 정보를 보여준다.
2. 응용 계층
   * 사용자가 요청한 기능을 실행한다. 업무 로직을 직접 구현하지 않으며 도메인 계층을 조합해서 기능을 실행한다.
3. 도메인
   * 시스템이 제공할 도메인 규칙을 구현한다.
4. 인프라스트럭처
   * 데이터베이스나 메시징 시스템과 같은 외부 시스템과의 연동을 처리한다.

* 처음에 언급되었던 도메인 모델은 도메인 자체를 이해하는 데 필요한 개념 모델을 의미했다.
* 지금 살펴볼 도메인 모델은 아키텍처 상의 도메인 계층을 객체 지향 기법으로 현하는 패턴을 말한다.
* 도메인 계층은 도메인의 핵심 규칙을 구현한다.
* 핵심 규칙을 구현한 코드는 도메인 모델에만 위치하기 때문에 규칙이 바뀌거나 규칙을 확장해야 할 때 다른 코드에 영향을 덜 주고 변경 내역을 모델에 반영할 수 있게 된다.

### 주문 도메인을 구현해보자
> * 출고 전에 배송지를 변경할 수 있다.
> * 주문 취소는 배송 전에만 할 수 있다.

```java
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

public enum OrderState {
    PAYMENT_WAITING {
        @Override
        public boolean isAfterDelivered() {
            return false;
        }
    },
    PREPARING {
        @Override
        public boolean isAfterDelivered() {
            return false;
        }
    },
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED;

    public boolean isAfterDelivered() {
        return true;
    }
}

public class ShippingInfo {
    private final String zipCode;
    private final String address;
    private final String receiverName;
    private final String receiverPhoneNumber;

    public ShippingInfo(String zipCode, String address, String receiverName, String receiverPhoneNumber) {
        this.zipCode = zipCode;
        this.address = address;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInfo that = (ShippingInfo) o;
        return Objects.equals(zipCode, that.zipCode) && Objects.equals(address, that.address) && Objects.equals(receiverName, that.receiverName) && Objects.equals(receiverPhoneNumber, that.receiverPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, address, receiverName, receiverPhoneNumber);
    }
}
```
```java
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
```

## 1.5 도메인 모델 도출
* 도메인을 모델링할 때 기본이 되는 작업은 모델을 구성하는 핵심 구성요소, 규칙, 기능을 찾는 것이다.
* 도메인 모델 패턴은 개발을 진행함에 따라 도메인에 대한 이해도가 더 깊어지기 마련이다.
* 도메인을 구현하다 보면 특정 조건이나 상태에 따라 제약이나 규칙이 달리 적용되는 경우가 많다.
* 더 깊어진 도메인 지식을 바탕으로 도메인의 핵심 규칙이 코드에 잘 드러나도록 지속적인 개선이 필요하다.

### 요구사항 정리
> * 최소 한 종류 이상의 상품을 주문해야 한다.
> * 한 상품을 한 개 이상 주문할 수 있다.
> * 총 주문 금액은 각 상품의 구매 가격 합을 모두 더한 금액이다.
> * 각 상품의 구매 가격 합은 상품 가격에 구매 개수를 곱한 값이다.
> * 주문할 때 배송지 정보를 반드시 지정해야 한다.
> * 배송지 정보는 받는 사람 이름, 전화번호, 주소로 구성된다.
> * 출고를 하면 배송지를 변경할 수 없다.
> * 출고 전에 주문을 취소할 수 없다.
> * 고객이 결제를 완료하기 전에는 상품을 준비하지 않는다.

```java
// 최소 한 종류 이상의 상품을 주문해야 한다.
class OrderTest {
    @Test
    void 주문은_최소_한_종류_이상의_상품을_주문해야_한다() {
        // given
        ShippingInfo shippingInfo = new ShippingInfo("111111", "서울", "자바", "01012341234");
        OrderState currentOrderState = OrderState.PAYMENT_WAITING;
        List<OrderLine> orderLines = Arrays.asList(new OrderLine());

        // when
        Order order = new Order(shippingInfo, currentOrderState, orderLines);

        // then
        assertThrows(IllegalArgumentException.class, () -> new Order(shippingInfo, currentOrderState, Collections.emptyList()));
        assertThat(order.getOrderLines().size()).isGreaterThan(0);
    }
}
```

```java
// 한 상품을 한 개 이상 주문할 수 있다.
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
```

```java
// 총 주문 금액은 각 상품의 구매 가격 합을 모두 더한 금액이다.
class OrderTest {
    
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
        int expectedTotalAmounts = orderLines.stream().mapToInt(OrderLine::getAmounts).sum();

        // when
        Order order = new Order(shippingInfo, currentOrderState, orderLines);

        // then
        assertThat(order.getTotalAmounts()).isEqualTo(expectedTotalAmounts);
    }
}
```

### 문서화
* 문서화를 하는 주된 이유는 지식을 공유하기 위함이다.
* 전반적인 기능 목록이나 모듈 구조, 빌드 과정은 코드를 보고 직접 이해하는 것보다 사우이 수준에서 정리한 문서를 참조하는 것이 소프트웨어 전반을 빠르게 이해하는 데 도움이 된다.
* 코드를 보면서 도메인을 깊게 이해하게 되므로 코드 자체도 문서화의 대상이 된다. 코드는 도메인 지식이 잘 드러나도록 작성해야한다.

## 1.6 엔티티와 밸류

### 엔티티
* 엔티티는 식별자를 가진다.
* 식별자는 엔티티 객체마다 고유해서 각 엔티티는 서로 다른 식별자를 갖는다.
* 엔티티의 식별자는 바뀌지 않고 고유하기 때문에 두 엔티티 객체의 식별자가 같으면 두 엔티티는 같다고 판단할 수 있다.

### 밸류
* 밸류 타입은 개념적으로 완전한 하나를 표현할 때 사용한다.
* 밸류 타입은 꼭 두 개 이상의 데이터를 가져야 하는 것은 아니다. 의미를 명확하게 표현하기 위해 밸류 타입을 사용하는 경우도 있다.

```java
public class OrderLine {
    private Product product;
    private int price;
    private int quantity;
}
```
* OrderLine의 price는 int 타입이지만 밸류 타입을 통해 돈이라는 의미를 부여할 수 있도록 Money 타입을 생성하여 가독성을 높일 수 있다.
```java
public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }

    public Money multiply(int quantity) {
        return new Money(value * quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
```
* 밸류 객체의 데이터를 변경할 때는 기존 데이터를 변경하는 것보다 변경한 데이터를 갖는 새로운 밸류 객체를 생성하는 방식이 좋다.
* 밸류 타입을 불변으로 구현하는 이유는 참조 투명성과 스레드에 안전한 코드를 작성하기 위함이다.
* 밸류 타입 클래스는 equals()와 hashCode()를 구현해주는 것이 좋다.

### 엔티티 식별자와 밸류 타입
* 단순히 리터럴 값이 아닌, 밸류 타입을 이용하여 해당 값에 의미를 부여하면 가독성이 늘어난다.

### 도메인 모델에 set 메서드 넣지 않기
* setter는 도메인의 핵심 개념이 드러나지 않는다.
* 도메인 객체를 생성할 때 온전하지 않은 상태가 될 수 있다.

## 1.7 도메인 용어와 유비쿼터스 언어
* 전문가, 관계자, 개발자가 도메인과 관련된 공통의 언어를 만들고 이를 대화, 문서, 도메인 모델, 코드, 테스트 등 모든 곳에서 같은 용어를 사용한다.
* 소통 과정에서 발생하는 용어의 모호함을 줄일 수 있고 개발자는 도메인과 코드 사이에서 불필요한 해석을 줄일 수 있다.
* 시간이 지날수록 도메인에 대한 이해가 높아지는데 새롭게 이해한 내용을 잘 표현할 수 있는 용어를 찾아내고 이를 다시 공통의 언어로 만들어 다같이 사용한다.
* 새로 발견한 용어는 코드나 문서에도 반영해서 산출물에 최신 모델을 적용한다.
* 도메인 용어에 알맞은 단어를 찾는 시간을 아끼워하지 말자.