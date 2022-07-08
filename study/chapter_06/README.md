# Chapter 6. 응용 서비스와 표현 영역

## 표현 영역과 응용 영역
* 표현 영역은 사용자의 요청을 해석한다.
* 실제 사용자가 원하는 기능을 제공하는 것은 응용 영역에 위치한 서비스다.
* 응용 서비스는 기능을 실행하는 데 필요한 입력 값을 메서드 인자로 받고 실행 결과를 리턴한다.
* 응용 서비스의 메서드가 요구하는 파라미터와 표현 영역이 사용자로부터 전달받은 데이터는 형식이 일치하지 않기 때문에 표현영역은 응용 서비스가 요구하는 형식으로 사용자 요청을 변환한다.

## 응용 서비스의 역할
* 응용 서비스는 사용자가 요청한 기능을 실행하고, 사용자의 요청을 처리하기 위해 리포지터리에서 도메인 객체를 가져와 사용한다.
* 응용 서비스가 복잡하다면 응용 서비스에서 도메인 로직의 일부를 구현하고 있을 가능성이 높다.
  * 응용 서비스가 도메인 로직을 일부 구현하면 코드 중복, 로직 분산 등 코드 품질에 안 좋은 영향ㅇ르 줄 수 있다.
* 응용 서비스는 트랜잭션 처리도 담당한다.
* 도메인 로직을 도메인 영역과 응용 서비스에 분산해서 구현하면 코드 품질에 문제가 발생한다.
  * 도메인 데이터와 그 데이터를 조작하는 도메인 로직이 한 영역에 위치하지 않고 다른 영역에 위치한다는 것은 도메인 로직을 파악하기 위해 여러 영역을 분석해야 한다.
  * 여러 응용 서비스에서 동일한 도메인 로직을 구현할 가능성이 높아진다. (중복 코드)

## 응용 서비스의 구현
* 응용 서비스는 표현 영역과 도메인 영역을 연결하는 매개체 역할을 하는데 이는 파사드(facade)와 같은 역할을 한다.
* 응용 서비스는 보통 다음의 두 가지 방법 중 한 가지 방식으로 구현한다.
  * 한 응용 서비스 클래스에 회원 도메인의 모든 기능 구현하기
  * 구분되는 기능별로 응용 서비스 클래스를 따로 구현하기

#### 한 응용 서비스 클래스에 회원 도메인의 모든 기능 구현하기
* 한 도메인과 관련된 기능을 구현한 코드가 한 클래스에 위치하므로 각 기능에서 동일 로직에 대한 코드 중복을 제거 할 수 있다.
* 한 서비스 클래스의 크기가 커진다는 것이 단점이다.
* 코드의 크기가 커지면 연관성이 적은 코드가 한 클래스에서 함께 위치할 가능성이 높아지게 되는데 결과적으로 관련없는 코드가 뒤섞여 코드를 이해하는 데 방해가 된다.

#### 구분되는 기능별로 응용 서비스 클래스를 따로 구현하기
* 클래스 개수는 많아지지만 한 클래스에 관련 기능을 모두 구현하는 것과 비교해서 코드 품질을 일정 수준으로 유지하는 데 도움이 된다.
* 각 클래스 별로 필요한 의존 객체만 포함하므로 다른 기능을 구현한 코드에 영향을 받지 않는다.
* 각 기능마다 동일한 로직을 구현할 경우 여러 클래스에 중복해서 동일한 코드를 구현할 가능성이 있다. 이러한 경우는 별도 클래스에 로직을 구현해서 코드의 중복을 방지할 수 있다.

### 응용 서비스의 인터페이스와 클래스
* 인터페이스가 필요한 몇 가지 상황이 있는데, 그중 하나가 구현 클래스가 여러 개인 경우다.
* 구현 클래스가 다수 존재하거나 런타임에 구현 객체를 교체해야 할 때 인터페이스를 유용하게 사용할 수 있다.
  * 하지만 응용 서비스는 런타임에 교체하는 경우가 거의 없고 한 응용 서비스의 구현 클래스가 두 개인 경우도 드물다.
* 이런 이유로 인터페이스와 클래스를 따로 구현하면 소스 파일만 많아지고 구현 클래스에 대한 간접 참조가 증가해서 전체 구조가 복잡해진다.

```text
개인적으로 동의하기 어렵다.
무분별하게 소스 파일이 많아지는건 문제이지만, TDD 통해 지속적으로 리팩토링을 한다면 문제가 될 정도로 많아지지 않는다고 생각한다. (물론 인터페이스를 활용하지 않는 것보다는 많겠지만)
애초에 책의 앞에서도 하나의 애거리거트는 도메인 지식이 넓어지고 애그리거트가 잘 나눠질수록 하나의 도메인은 점점 작아진다고 하지 않았는가?
이렇게 작아진 크기에서 인지 과부화가 생길정도의 소스코드가 생겨난다면 오히려 설계 구조가 잘못되어 있는건 아닌지 점검할 필요가 있다고 생각한다.
인터페이스를 활용하지 않으면 TDD를 진행하다가 해당 기능에서 다른 협력 객체를 필요로 한다면 이를 위해 TDD를 하다말고 구체 클래스를 구현해야한다.

그리고 구체 클래스의 대용으로 Mockito와 같은 도구를 이용하여 테스트 더블을 사용하면 된다는 것도 동의하기 어렵다.
행위 기반 검증을 위한 테스트라면 시나리오를 테스트하기 위한 좋은 방법이지만 단순히 구체 클래스 대용으로 Mocking을 하는 것은 테스트를 하고자 하는 클래스가 어떻게 협력할지 이미 알고 작성을 해야한다.
이는 테스트 코드의 가독성을 저해시키는 요소가 될 수 있고, 테스트하고자 하는 기능의 목적이 희석된다.
Mock 객체에서 행위를 설정할 때, 실제 구현체가 그렇게 동작하기를 기대하면서 작성을 하는데, 실제 구현체에서는 의도한대로 동작하지 않을 수 있기 때문에 테스트의 신뢰도가 떨어질 수도 있다.
```

### 표현 영역에 의존하지 않기
* 응용 서비스는 표현 영역과 관련된 타입을 사용하면 안 된다.
* 응용 서비스에서 표현 영역에 대한 의존이 발생하면 응용 서비스만 단독으로 테스트하기가 어려워진다.
  * 표현 영역 구현의 변경이 응용 계층의 변경으로 이어지는 문제가 발생한다.

### 트랜잭션 처리
* 프레임워크가 제공하는 트랜잭션 기능을 사용하면 트랜잭션 처리 코드를 간결하게 유지할 수 있다.

## 표현 영역
* 사용자가 시스템을 사용할 수 있는 엔드 포인트를 제공하고 제어한다.
* 사용자의 요청을 알맞은 응용 서비스에 전달하고 결과를 사용자에게 제공한다.
* 사용자의 세션을 관리한다.

## 값 검증
* 값 검증은 표현 영역과 응용 서비스 두 곳에서 모두 수행할 수 있다.
* 원칙적으로 모든 값에 대한 검증은 응용 서비스에서 처리한다.
  * 표현 영역: 필수 값, 값의 형식, 범위 등을 검증한다.
  * 응용 서비스: 데이터의 존재 유무와 같은 논리적 오류를 검증한다.

```text
비즈니스 룰을 위한 검증을 위해 도메인 영역에서도 검증할 수 있다.
비즈니스 룰은 도메인의 정책이고, 이를 검증할 책임은 도메인에 있다. 도메인은 객체가 조건을 만족하는지에 대한 조건은 명세를 통해 검증할 수 있다.
명세를 활용하면 비즈니스 룰 검증을 응집력 있게 설계할 수 있고, 테스트하기도 용이해진다.
```

## 조회 전용 기능과 응용 서비스
* 조회이 서비스에서 수행하는 추가적인 로직이 없고, 단일 쿼리만 실행한다면 굳이 서비스를 만들 필요 없이 표현 영역에서 바로 조회 전용 기능을 사용해도 괜찮은 방법이다.