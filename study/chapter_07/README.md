# Chapter 07. 도메인 서비스

## 여러 애그리거트가 필요한 기능
* 도메인 영역 코드를 작성하다 보면 한 애그리거트로 기능을 구현할 수 없을 때가 있다.
* 대표적인 예가 결제 금액 계산 로직이다.
  * 상품 애그리거트: 구매하는 상품의 가격이 필요하다. 상품에 따라 배송비가 추가되기도 한다.
  * 주문 애그리거트: 상품별로 구매 개수가 필요하다
  * 할인 쿠폰 애그리거트: 쿠폰별로 지정한 할인 금액이나 비율에 따라 주문 총 금액을 할인한다.
  * 회원 애그리거트: 회원 등급에 따라 추가 할인이 가능하다.
* 위와 같은 상황에서 생각해 볼 수 있는 방법은 주문 애그리거트가 필요한 데이터를 모두 가지도록 한 뒤 할인 금액 게산 책임을 주문 애그리거트에 할당하는 것이다.
* 결제 금액 계산 로직이 주문 애그리거트의 책임이 맞는지 고민해볼 필요가 있다.
* 할인 정책이 새롭게 생긴다면 주문 애그리거트가 갖고 있는 구성요소와는 관련이 없음에도 불구하고 결제 금액 계산 책임이 주문 애그리거트에 있다는 이유로 주문 애그리거트 코드를 수정해야 하는 문제가 발생한다.
* 이렇듯 한 애그리거트에 넣기 애매한 도메인 기능을 억지로 특정 애그리거트에 구현하면 자신의 책임 범위를 넘어서기 때문에 외부에 대한 의존이 높아지고 코드가 복잡해져서 수정하기 어렵게 만드는 요인이 된다.
* 이런 문제는 도메인 기능을 별도 서비스로 구현하면 해결할 수 있다.

## 도메인 서비스
* 도메인 서비스는 도메인 영역에 위치한 도메인 로직을 표현할 때 사용한다.
  * 계산 로직: 여러 애그리거트가 필요한 계산 로직이나, 한 애그리거트에 넣기에는 다소 복잡한 계산 로직
  * 외부 시스템 연동이 필요한 도메인 로직: 구현하기 위해 타 시스템을 사용해야 하는 도메인 로직

### 계산 로직과 도메인 서비스
* 한 애그리거트에 넣기 애매한 도메인 개념을 구현하려면 애그리거트에 억지로 넣기보다는 도메인 서비스를 이용해서 도메인 개념을 명시적으로 드러내면 된다.
* 응용 영역의 서비스가 응용 로직을 다룬다면 도메인 서비스는 도메인 로직을 다룬다.
* 도메인 영역의 애그리거트나 밸류와 같은 구성요소와의 차이점은 메인 서비스는 상태 없이 로직만 구현한다는 점이다.
* 도메인 서비스를 사용하는 주체는 애그리거트가 될 수도 있고, 응용 서비스가 될 수도 있다.
* 애그리거트 객체에 도메인 서비스를 전달하는 것은 응용 서비스 책임이다.
* 애그리거트 메서드를 실행할 때 도메인 서비스를 인자로 전달하지 않고 반대로 도메인 서비스의 기능을 실행할 때 애그리거트를 전달하기도 한다.
  * 도메인 서비스는 도메인 로직을 수행하지 응용 로직을 수행하진 않는다.
  * 트랜잭션 처리와 같은 로직은 응용 로직이다.

### 외부 시스템 연동과 도메인 서비스
* 외부 시스템이나 타 도메인과의 연동 기능도 도메인 서비스가 될 수 있다.
* 도메인 로직 관점에서 인터페이스를 작성하고, 타 시스템과 연동한다는 관점에서 인터페이스를 작성해서는 안된다.
  * 이 인터페이스는 인프라스트럭처 영역에서 구현된다.
  * 책에서는 연동 기능을 포함한다고 하는데, 연동 기능은 애플리케이션 계층에서 설정해도 괜찮은 것 같다.

### 도메인 서비스의 패키지 위치
* 도메인 서비스는 도메인 로직을 표현하므로 도메인 서비스의 위치는 다른 도메인 구성요소와 동일한 패키지에 위치한다.

### 도메인 서비스의 인터페이스와 클래스
* 도메인 서비스의 로직이 고정되어 있지 않은 경우 도메인 서비스 자체를 인터페이스로 구현하고 이를 구현한 클래스를 둘 수 있다.
  * 애초에 인터페이스로 추상화하여 구현하는게 좋다고 생각한다.
* 외부 시스템이나 별도 엔진을 이용해서 구현할 때 인프라스트럭처 영역에서 이를 구현하면 된다.