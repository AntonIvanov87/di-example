package ru.hh.diexample.springjava;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hh.diexample.settings.Settings;

import javax.sql.DataSource;
import java.sql.SQLException;

/** spring configuration that declares beans only for production (not testing) purposes */
@Configuration
class SpringProdOnlyConfig {

  @Bean
  Settings settings() {
    final Config config = ConfigFactory.load();
    return new Settings(config);
  }

  @Bean
  String databaseUrl() {
    return settings().databaseUrl();
  }

  @Bean
  String databaseUser() {
    return settings().databaseUser();
  }

  @Bean
  String databasePassword() {
    return settings().databasePassword();
  }
  // notice that default scope in spring is singleton
  // guess: how many times settings() method will be actually called?

  @Bean
  DataSource dataSource() throws SQLException {
    final PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
    pgSimpleDataSource.setUrl(databaseUrl());
    pgSimpleDataSource.setUser(databaseUser());
    pgSimpleDataSource.setPassword(databasePassword());
    return pgSimpleDataSource;
  }
}
