# Spring Boot WebAPI Testing

### Create Project
https://start.spring.io/

- Spring Web Starter
- Lombok
- Spring Data JPA
- PostgreSQL Driver

### Maven Install

```
mvn install
```

#### Notice
- H2を使用しない場合、datasourceの設定をしないとERRORになる

application.properties
```
# Database config
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
```

- Due to test phase, ERROR occurs. add `-Dmaven.test.skip=true` option.

### 仕様

### メモ
- SQLをファイル実行する
```
\i ./sql/postgres.sql
```