# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository purpose

This is a personal technology learning/demo repository (`yzh.com:parent`), not a single application. Each top-level directory is an independent Maven module used to explore one specific library, framework, or JVM concept (Netty, RabbitMQ, RocketMQ, ZooKeeper/Curator, Flink, Redis/Jedis, Guava, Ehcache, Memcached, JVM internals, classloaders, bytecode tools like Javassist/AspectJ/BTrace, algorithms, design patterns, Spring Boot auto-configuration, etc.). Most code is exercised via `main()` methods rather than automated tests — treat modules as sandboxes, not production services.

## Build commands

- Build everything: `mvn clean install` (or `mvn clean package`) from the repo root.
- Build/compile a single module: `mvn -pl <module> -am clean install` (e.g. `mvn -pl netty -am install`).
- Run a class with a `main()` method: `mvn -pl <module> exec:java -Dexec.mainClass="fully.qualified.ClassName"` (no `exec-maven-plugin` is currently configured in the parent POM, so prefer running/debugging main classes directly from the IDE unless you add the plugin).
- Run tests: `mvn -pl <module> test`. Automated test coverage is minimal (JUnit 4); most modules have none. The `middleware` module has a Spring Boot test scaffold at `middleware/src/test/java/yzh/com/test/` (`Application` + `BaseTest`, using `@RunWith(SpringRunner.class)` + `@SpringBootTest`) — follow that pattern if adding Spring-based tests there.
- Java target: 1.8 (set via `maven-compiler-plugin` in the root `pom.xml`).
- Maven repositories are pinned to the Aliyun mirror (`repositories`/`pluginRepositories` in root `pom.xml`) instead of Maven Central — keep this if editing repo config.

## Architecture / structure

- Root `pom.xml` is an aggregator POM (`packaging: pom`) listing every module and centralizing dependency **versions** in `<properties>` and **managed dependencies** in `<dependencyManagement>`. When adding a new third-party dependency used by more than one module, add its version property + managed entry in the root POM rather than hardcoding versions in child modules.
- Each module is self-contained: its own `pom.xml` (parented to the root `yzh.com:parent`), its own `src/main/java` package namespace (namespaces are inconsistent across modules — some use `yzh.com.*`, others `yzh.*` or a bare package matching the topic, e.g. `flink.*`), and no cross-module dependencies between the topic modules themselves.
- Some modules are empty placeholders reserved for future exploration (e.g. `gts`, `agent` currently have only a `pom.xml` with no source).
- `middleware` is the most structurally significant module: it demonstrates writing a Spring Boot auto-configuration starter — `META-INF/spring.factories` registers three `@Configuration` classes (`MiddleWareAutoConfiguration`, `MiddleWareAutoConfiguration1`, `MiddleWareAutoConfiguration2`) that are auto-loaded by any Spring Boot app depending on this module. Read `middleware/src/main/java/yzh/com/middleware/config/` together (properties binding via `@EnableConfigurationProperties`, `BeanPostProcessor`, `@PropertySource` loading order) to understand how the pieces fit — the auto-configuration behavior here spans multiple files and isn't obvious from any single class.
- `flink` contains DataStream API demos under `flink/src/main/java/flink/datastream/` (fraud detection, windowing/reduce examples) — these are meant to be run individually, not as a deployed job.
- When adding a new demo topic, follow the existing pattern: create a new top-level module directory, add a minimal `pom.xml` parented to the root, register it in the root `pom.xml` `<modules>` list, and prefer runnable `main()` classes over test suites unless the topic is specifically about testing.
