# 🧭 Spring + Java Clean Architecture Guide

> 이 문서는 **Spring + Java 기반의 Clean Architecture 구조**를 이해하고 실무에 적용하기 위한 가이드입니다.  
> 목적은 **비즈니스 로직의 독립성 확보**, **유연한 확장성**, **테스트 용이성**을 달성하는 것입니다.

---

## 🧩 1. Clean Architecture란?

> "의존성의 방향을 항상 **비즈니스 로직(도메인)** 쪽으로 향하게 하라."

Clean Architecture는 기존의 Layered Architecture보다 더 엄격하게 **의존성 규칙**을 정의합니다.  
즉, **도메인 중심의 설계(Domain-Centric Design)** 을 실현하여 프레임워크, DB, 외부 API에 의존하지 않는 구조를 만듭니다.

---

## 🏗️ 2. 기본 구조

```
┌──────────────────────────────────────┐
│          Presentation Layer          │ ← Controller, DTO
├──────────────────────────────────────┤
│          Application Layer           │ ← UseCase, Service
├──────────────────────────────────────┤
│            Domain Layer              │ ← Entity, Domain Logic
├──────────────────────────────────────┤
│        Infrastructure Layer          │ ← Repository, External API
└──────────────────────────────────────┘
```

### ✅ 의존성 방향
```
Infrastructure  →  Application  →  Domain
Controller      →  Application  →  Domain
```
> Domain은 절대 외부 계층을 의존하지 않습니다.

---

## ⚙️ 3. 계층별 역할과 책임

| 계층 | 역할 | 핵심 포인트 |
|------|------|-------------|
| **Domain** | 핵심 비즈니스 로직 보유 | Entity, Value Object, Domain Service |
| **Application** | 비즈니스 시나리오(Use Case) 구현 | 트랜잭션, 흐름 제어 |
| **Presentation** | HTTP 요청/응답 처리 | Controller, Request/Response DTO |
| **Infrastructure** | 외부 시스템과의 연결 담당 | Repository 구현체, API, File 등 |

---

## 🧠 4. Domain Layer

> 도메인은 **시스템의 핵심 규칙과 불변의 비즈니스 로직**을 표현합니다.  
> 외부 기술(JPA, DB, API 등)에 절대 의존하지 않습니다.

```java
// domain/model/User.java
public class User {
    private final String name;
    private final String encodedPassword;

    public User(String name, String encodedPassword) {
        this.name = name;
        this.encodedPassword = encodedPassword;
    }

    public boolean matchesPassword(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.encodedPassword);
    }
}
```

---

## 🧩 5. Application Layer (Use Case)

> UseCase는 "사용자가 수행할 수 있는 행위"를 나타냅니다.  
> 트랜잭션, 유효성 검사, 도메인 객체 조립을 담당합니다.

```java
// application/usecase/CreateUserUseCase.java
@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(String name, String rawPassword) {
        String encoded = passwordEncoder.encode(rawPassword);
        User user = new User(name, encoded);
        return userRepository.save(user);
    }
}
```

> Application 계층은 도메인 모델을 활용하지만, JPA나 DB를 직접 사용하지 않습니다.  
> 대신 `UserRepository` 인터페이스를 의존합니다.

---

## 🗄️ 6. Infrastructure Layer (Adapter)

> DB, 외부 API 등 실제 구현이 이루어지는 계층입니다.  
> Application 또는 Domain이 정의한 인터페이스를 구현합니다.

```java
// infrastructure/persistence/JpaUserRepository.java
@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {
    private final SpringDataUserRepository jpaRepo;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        return jpaRepo.save(entity).toDomain();
    }

    @Override
    public Optional<User> findByName(String name) {
        return jpaRepo.findByName(name).map(UserEntity::toDomain);
    }
}
```

**SpringDataUserRepository 예시**
```java
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}
```

---

## 🧱 7. Presentation Layer

> 외부 요청과 응답을 담당하며, DTO 변환 및 유효성 검증을 수행합니다.

```java
// presentation/controller/UserController.java
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        User user = createUserUseCase.createUser(request.getName(), request.getPassword());
        return ResponseEntity.ok(new UserResponse(user));
    }
}
```

---

## 🔧 8. 의존 구조 (텍스트 시각화)

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

- Application 계층은 UserRepository 인터페이스만 의존합니다.
- Infrastructure가 구체 구현체를 제공하며, DB 접근을 수행합니다.
- Domain은 DB나 Spring 기술을 전혀 알 필요가 없습니다.

---

## 🧪 9. 테스트 용이성

| 계층 | 테스트 방법 | 의존성 |
|------|--------------|--------|
| **Domain** | 순수 단위 테스트 | 없음 |
| **Application** | Mock Repository 사용 | `Mockito` 등 |
| **Infrastructure** | H2 등으로 통합 테스트 | 실제 DB 환경 |
| **Presentation** | MockMvc, RestAssured | API 단위 검증 |

---

## 🚀 10. Clean Architecture의 장점

| 항목 | 효과 |
|------|------|
| **테스트 용이성** | 각 계층 독립 테스트 가능 |
| **유연한 확장성** | DB, 외부 API, Queue 교체 용이 |
| **높은 유지보수성** | 변경 영향 최소화 |
| **명확한 책임 분리** | Controller, UseCase, Domain, Infra 역할 명확 |
| **도메인 중심 설계** | 기술이 아닌 비즈니스 로직 중심 구조 |

---

## ✅ 결론

> Clean Architecture는 Layered Architecture의 발전형으로,  
> **비즈니스 로직 중심의 계층 분리**를 통해  
> 시스템의 **유연성, 테스트 용이성, 유지보수성**을 극대화한다.

---

**Keywords:** Spring Boot, Java, OOP, Clean Architecture, Hexagonal, Domain-Driven Design, TDD
