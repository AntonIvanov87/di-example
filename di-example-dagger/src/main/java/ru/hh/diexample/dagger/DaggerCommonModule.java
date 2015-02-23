package ru.hh.diexample.dagger;

import dagger.Module;
import dagger.Provides;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.hh.diexample.users.UserDAO;
import ru.hh.diexample.users.UserSpringJDBCDAO;

import javax.inject.Singleton;
import javax.sql.DataSource;

/** dagger module that declares beans common for production and testing purposes */
@Module(injects = UserDAO.class, complete = false)
class DaggerCommonModule {

  @Provides
  @Singleton
  JdbcTemplate provideJdbcTemplate(final DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Provides
  @Singleton
  UserDAO provideUserDAO(final JdbcTemplate jdbcTemplate) {
    return new UserSpringJDBCDAO(jdbcTemplate);
  }
}
