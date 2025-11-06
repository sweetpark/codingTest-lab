# 🧱 Spring + Java Backend Architecture Guide
> 객체지향적 설계, DI/IoC 기반 계층 구조, 다형성, Clean Architecture 확장, 그리고 테스트 친화 아키텍처 설계 정리

---

# 🧩 OOP + TDD + Coding Test Practice

## 1. 프로젝트 목적

> 이 프로젝트는 코딩테스트 문제를 단순히 푸는 것을 넘어, <br> **객체지향적 설계(OOP)** 와 **테스트 주도 개발(TDD)** 을
> 통해 실무 수준의 설계 및 코드 품질 향상을 목표로 한다. <br> <br> 1. 각 문제를 하나의 **기능 단위**로 보고,
<br> 2. 객체 간의 **책임 분리**와 재사용 가능한 구조를 연습한다. <br> 3. **테스트 케이스를** 통해 단위 테스트 기반의 개발 사이클을 체득한다.

---

## 2. 객체지향 설계 규칙 (SOLID Principles)

- **SRP (단일 책임 원칙)** : 클래스는 **하나의 책임**만 가져야 한다.
- **OCP (개방-폐쇄 원칙)** : **확장에는 열려 있고**, 수정에는 닫혀 있어야 한다.
- **LSP (리스코프 치환 원칙)** : 자식 클래스는 **부모 클래스의 행위를 보장**해야 한다. (상속잘받기)
- **ISP (인터페이스 분리 원칙)** : 사용하지 않는 **인터페이스에 의존**하지 않는다.
- **DIP (의존 역전 원칙)** : 고수준 모듈은 **저수준 모듈에 의존**하지 않는다.

---

## 3. 단위테스트 기반 TDD 설계 방법

> **TDD(Test Driven Development)** 는 테스트를 먼저 작성하고, 해당 테스트를 통과하는 최소한의 코드를 작성한 후,
> 리팩토링을 수행하는 개발 사이클이다.

1. **Red 단계** : 실패하는 테스트 작성 (요구사항 명확화)
2. **Green 단계** : 테스트를 통과하는 최소한의 코드 구현
3. **Refactor 단계** : 코드 중복 제거 및 구조 개선

---

## 4. 주로 사용하는 인텔리제이 단축키

| 기능          | 단축키                    |
|-------------|------------------------|
| 전체 검색       | `Shift + Shift`        |
| 테스트 클래스 생성  | `Ctrl + Shift + T`     |
| 클래스 / 파일 찾기 | `Ctrl + N / Shift + N` |
| 빠른 수정 및 제안  | `Alt + Enter`          |
| 선언부로 이동     | `Ctrl + B`             |
| 코드 자동 정렬    | `Ctrl + Alt + L`       |
| 한 줄 복사      | `Ctrl + D`             |
| 주석 처리       | `Ctrl + /`             |
| 현재 파일 실행    | `Ctrl + Shift + F10`   |
| 전체 실행       | `Ctrl + Shift + R`     |

---

## 5. 참고 사이트

- [프로그래머스](https://programmers.co.kr)
- [백준 온라인 저지](https://www.acmicpc.net)

---

## 🏁 Spring Architecture Flow

CRUD 요청이 들어오는 웹 애플리케이션에서의 기본 구조는 다음과 같다:

1. **Controller**: 클라이언트 요청을 수신한다.
2. **Service**: 비즈니스 로직을 수행한다.
3. **Repository**: DB 접근 및 데이터 조작을 담당한다.
4. **Controller**: 가공된 데이터를 응답 형태로 반환한다.

---

## 🧩 핵심 개념

| 개념 | 설명 | 실제 적용 포인트                                        |
|------|------|--------------------------------------------------|
| **IoC (Inversion of Control)** | 객체의 생성과 생명주기를 프레임워크(Spring)가 제어 | `@Component`, `@Service`, `@Repository`, `@Bean` |
| **DI (Dependency Injection)** | 객체를 내부에서 생성하지 않고 외부에서 주입 | 생성자 주입(`private final`)                          |
| **다형성 (Polymorphism)** | 상위 타입으로 여러 하위 구현체를 다룰 수 있음 | 인터페이스 기반 설계 or abstract(추상) class                |
| **OCP (Open-Closed Principle)** | 확장에는 열려 있고, 수정에는 닫혀 있어야 함 | 구현체 추가 시 기존 코드 수정 불필요                            |

---

## ⚙️ 계층별 역할과 설계 원칙

### Controller Layer

- 비즈니스 로직을 **몰라야 한다.**
- 역할: 요청(Request)을 받고 Service에 위임, 응답(Response)을 반환.
- 입력/출력 DTO를 사용하여 **도메인 캡슐화**.

```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
```

➡️ `UserService`는 **인터페이스 타입으로 주입**되어, 구체 구현체를 몰라도 된다.  
즉, Controller는 **흐름 제어자(Orchestrator)** 로만 존재한다.

---

### Service Layer
> 추상화 인터페이스 제공 (UserService)
```java
public interface UserService {
    UserResponse createUser(UserCreateRequest request);
}
```

---
>구현체 (UserServiceImpl)

```java
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        User user = new User(request.getName(), passwordEncoder.encode(request.getPassword()));
        return new UserResponse(userRepository.save(user));
    }
}
```

---

### Repository Layer

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
```

---

### Domain Layer

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String encodedPassword;

    public User(String name, String encodedPassword) {
        this.name = name;
        this.encodedPassword = encodedPassword;
    }
    
    // 도메인 객체에서 사용되는 메서드 (필드값 직접 적용 x > pulbic 메서드 이용)
    public void changePassword(String newEncodedPassword) {
        this.encodedPassword = newEncodedPassword;
    }
}
```

---

## 🧭 Clean Architecture 확장 구조

### 핵심 철학
> “의존성의 방향을 **도메인**으로만 향하게 하라.”
> > 1. 메서드는 하나의 행위 (ex 비밀번호 변경 메서드)
> > 2. 객체는 하나의 책임 (ex 사용자 관리 책임)

---

### 계층 구조

```
┌──────────────────────────────────────┐
│          Presentation Layer          │ ← Controller, DTO (엔드포인트)
├──────────────────────────────────────┤
│          Application Layer           │ ← UseCase(책임), Service (실제 비지니스 로직)
├──────────────────────────────────────┤
│            Domain Layer              │ ← Entity, Domain Logic (사용되는 도메인 객체들)
├──────────────────────────────────────┤
│        Infrastructure Layer          │ ← Repository, External API (비지니스로직에 필요한 요소들)
└──────────────────────────────────────┘
```

---

### SpringDataUserRepository의 역할

| 클래스명 | 위치 | 역할                                                               |
|-----------|------|------------------------------------------------------------------|
| **`UserRepository`** | Domain | “User 데이터를 저장/조회해야 한다”는 행위 정의 (Interface)                        |
| **`SpringDataUserRepository`** | Infrastructure | Spring Data JPA가 자동 구현해주는 Repository                             |
| **`JpaUserRepository`** | Infrastructure | `UserRepository`를 구현하고 내부에서 `SpringDataUserRepository`를 이용해 DB 접근 |

**SpringDataUserRepository 예시**
```java
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}
```

---

## 🔧 의존 구조 (텍스트 시각화)

```
Application (UseCase)
        ↓
UserRepository (interface)
        ↓
JpaUserRepository (implements UserRepository)
        ↓
SpringDataUserRepository (extends JpaRepository)
        ↓
Database
```

---

### ✅ 결론

> “Clean Architecture는 Layered Architecture의 발전형으로,  
> **의존성의 방향을 비즈니스 로직 쪽으로만 흐르게 하여**  
> 시스템의 유연성, 테스트 용이성, 유지보수성을 극대화한다.”

---


