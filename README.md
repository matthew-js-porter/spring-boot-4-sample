# Spring Boot 4 Demo 🍃 [![Build](https://github.com/matthew-js-porter/spring-boot-4-sample/actions/workflows/maven.yml/badge.svg)](https://github.com/matthew-js-porter/spring-boot-4-sample/actions/workflows/maven.yml)

A sample project showcasing Spring Boot 4 features and capabilities.

## Prerequisites

- Java 21+
- Presenterm

## Quick Start

```bash
# Clone and run
./mvnw spring-boot:run

# Test endpoints
http :8080/hello version==1
http :8080/hello version==2
```

## New Features

### Modular Dependencies
Spring Boot 4.0 has a new modular design and now ships smaller focused modules rather than several large jars.
This greatly reduces the size of `spring-boot-autoconfigure` jar and produces Spring Boot application with smaller footprints.

This sample leverages the new `spring-boot-starter-webmvc` and `spring-boot-starter-restclient` starters.

[`pom.xml`](pom.xml)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-restclient</artifactId>
</dependency>
```


### API Versioning
Spring Boot 4.0 brings in built in versioning support.
An example of this can be found in this Sample:


[`HelloController.java`](src/main/java/com/example/demo/HelloController.java)
```java
@RestController
public class HelloController {

    @GetMapping(value = "/hello", version = "1")
    String hello() {
        return "Hello World";
    }

    @GetMapping(value = "/hello", version = "2")
    String hello2() {
        return "Hello There!";
    }
}
```

Supports versioning via:
- Query parameters (`?version=1`)
- Headers (`X-API-Version: 1`)
- Path variables (`/v1/hello`)
- Media type parameters

This sample uses `query-parameter` and defines it in the `application.yml`.


[`application.yml`](src/main/resources/application.yml)
```yaml
spring:
  mvc:
    apiversion:
      use:
        query-parameter: version
```


### HTTP Service Client
Declarative HTTP clients can now be configured with autoconfiguration and also supports versioning.

An example of this can be found in this Sample:

[`HelloClient`](src/main/java/com/example/demo/HelloClient.java)
```java
@HttpServiceClient("hello")
public interface HelloClient {
    @GetExchange(value = "/hello", version = "2")
    String hello();
}
```

Configure base URLs in `application.yml`:
```yaml
spring:
  http:
    client:
      service:
        group:
          hello:
            base-url: http://localhost:8080
        apiversion:
          insert:
            query-parameter: version
```

### RestTestClient

Spring Framework 7 introduces `RestTestClient` for testing web services.
It has the same methods as `RestClient` but with assertions.

An example of this can be found in this Sample:

[`HelloControllerTest.java`](src/test/java/com/example/demo/HelloClientTest.java)

```java
@Test
void saysHello() {
    restTestClient.get().uri("/hello").apiVersion("1")
            .exchange().expectBody(String.class).isEqualTo("Hello World");
}
```


### JSpecify

Spring Framework 7 deprecates it's Nullable annotations in favor of [JSpecify](https://jspecify.dev/docs/start-here/) 
annotations, which provide significant enhancements such as properly defined
specifications, a canonical dependency with no split-package issues, better tooling, better Kotlin integration, and the
capability to specify nullability more precisely for more use cases.

An example of this can be found in this Sample, which marks the package as non-null by default:
[`package-info.java`](src/main/java/com/example/demo/package-info.java)
```java
@NullMarked
package com.example.demo;

import org.jspecify.annotations.NullMarked;
```


## Presentation

View the Spring Boot 4 presentation:

```bash
presenterm presentation.md
```