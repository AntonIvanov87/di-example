package ru.hh.diexample.springjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.hh.diexample.users.UserSpringJDBCDAO;

import javax.inject.Inject;
import javax.sql.DataSource;

/** spring configuration that declares beans common for production and testing purposes */
@Configuration
class SpringCommonConfig {

  @Inject  // configuration class can act as normal bean: that is you can inject dependencies into it
  DataSource dataSource;

  @Bean
  JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }
  // that roughly means:
  // <bean id="jdbcTemplate" class="JdbcTemplate">
  //   <constructor-arg ref="dataSource">
  // </bean>

  @Bean
  UserSpringJDBCDAO userSpringJDBCDAO() {
    return new UserSpringJDBCDAO(jdbcTemplate());
  }
}
