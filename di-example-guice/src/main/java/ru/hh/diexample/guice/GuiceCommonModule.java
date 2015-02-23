package ru.hh.diexample.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.hh.diexample.users.UserDAO;
import ru.hh.diexample.users.UserSpringJDBCDAO;

import javax.inject.Singleton;
import javax.sql.DataSource;

/** guice module that declares beans common for production and testing purposes */
class GuiceCommonModule extends AbstractModule {

  @Override  // guice's AbstractModule demands to implement void configure() method
  protected void configure() {
    bind(UserDAO.class).to(UserSpringJDBCDAO.class);
    // generally: bind(Abstract.class).to(Implementation.class);
    // that means: whenever bean demands dependency of type UserDAO - inject UserSpringJDBCDAO
    // notice that UserSpringJDBCDAO is annotated with @Singleton
    // therefore every bean that demands UserDAO will receive the same instance of UserSpringJDBCDAO
    // if do not want to touch class with @Singleton annotation, you can:
    // bind(Abstract.class).to(Implementation.class).in(Singleton.class);

    // see also:
    // bind(Implementation.class);
    // bind(UserDAO.class).toProvider(Provider<Implementation.class>);
  }

  // if you can not put @Inject annotations on bean - use a little more verbose @Provides methods
  @Provides
  @Singleton
  static JdbcTemplate provideJdbcTemplate(final DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
