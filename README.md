# DI EXAMPLE

[Core artifact](di-example-core) contains classes that implement simple dao.

These classes are wired together with:

- [Spring XML](di-example-spring-xml/src/main/java/ru/hh/diexample/springxml)
- [Spring annotations](di-example-spring-annotations/src/main/java/ru/hh/diexample/springannotations)
- [Spring java](di-example-spring-java/src/main/java/ru/hh/diexample/springjava)
- [Guice](di-example-spring-guice/src/main/java/ru/hh/diexample/guice)
- [Dagger](di-example-spring-dagger/src/main/java/ru/hh/diexample/dagger)

To run Main classes you need Postgresql installed.
After installation run [prepare-db-as-postgres.sh](di-example-core/src/main/sh/prepare-db-as-postgres.sh) to create tables and users.
