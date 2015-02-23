package ru.hh.diexample.springjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
class SpringTestOnlyConfig {

  @Bean(destroyMethod = "shutdown")  // nice! guice does not allow to do that, but there is workaround
  static EmbeddedDatabase dataSource() {

    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("create-tables.sql")
            .build();
  }
}
