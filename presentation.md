---
title: What's New in Spring Boot 4 🍃
options:
  end_slide_shorthand: true
theme:
  name: catppuccin-frappe
---
Timeline
===
- Spring Boot 4.0.0-M2 released on Aug 21, 2025
- Spring Boot 4.0.0-M3 releases on September 18, 2025
- Spring Boot 4.0.0-RC1 releases on October 23, 2025
- Spring Boot 4.0.0 releases on November 20, 2025
- Spring Cloud 2025.1.0 releases on November 24, 2025

[calendar.spring.io](https://calendar.spring.io/)

---

Major Dependency Upgrades
===

- Spring Framework 7 `🍃`
- Jakarta EE 11 (Servlet 6.1)
- Tomcat 11
- Kotlin 2
- Jackson 3 (Not Yet)
- Jspecify 🆕

---

Module Dependencies
===

- ships smaller focused modules rather than several large jars
- Reduces size on `spring-boot-autoconfigure` jar
- Separate `spring-boot-starter-webflux` and `spring-boot-starter-webclient`
- `spring-boot-starter-webmvc` successor of `spring-boot-starter-web`
- `spring-boot-starter-classic` for old behavior.
---


Versioning Support
===

```java
@GetMapping(value = "/hello", version = "1")
String hello() {
    return "Hello World";
}
```

```yaml
spring:
  mvc:
    apiversion:
      use:
        query-parameter: version
```

- query
- header
- path
- media-type parameter

---

Http Service Client
===

```java
@HttpServiceClient("hello")
public interface HelloClient {

    @GetExchange(value = "/hello")
    String hello();
}
```

```yaml
spring:
  http:
    client:
      service:
        group:
          hello:
            base-url: http://localhost:8080
```

---

RestTestClient
===

```java
restTestClient
        .get()
        .uri("/hello")
        .exchange()
        .expectBody(String.class)
        .isEqualTo("Hello World");
```

---

<!--alignment: center -->
<!--new_lines: 10 -->
<!--font_size: 7 -->
DEMO