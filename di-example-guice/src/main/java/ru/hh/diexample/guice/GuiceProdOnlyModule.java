package ru.hh.diexample.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.postgresql.ds.PGSimpleDataSource;
import ru.hh.diexample.settings.Settings;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.SQLException;

import static com.google.inject.name.Names.named;

/** guice module that declare beans only for production (not testing) purposes */
class GuiceProdOnlyModule extends AbstractModule{

  @Override
  protected void configure() {
    final Config config = ConfigFactory.load();
    final Settings settings = new Settings(config);

    // what if you have multiple instances of the same class?
    // for ex. many String settings:
    bind(String.class).annotatedWith(named("databaseUrl")).toInstance(settings.databaseUrl());
    bind(String.class).annotatedWith(named("databaseUser")).toInstance(settings.databaseUser());
    bind(String.class).annotatedWith(named("databasePassword")).toInstance(settings.databasePassword());
    // generally: bind(Abstract.class).annotatedWith(Names.named("foo")).to(FooImplementation.class);
    // that means: give this instance to those beans that demands not any String, but more specific one: annotated with "foo"
  }

  @Provides
  @Singleton
  static DataSource provideDataSource(
          @Named("databaseUrl") final String url,  // that means: I need not any String, but more specific one: annotated with "databaseUrl"
          @Named("databaseUser") final String user,
          @Named("databasePassword") final String password) throws SQLException {

    final PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setUrl(url);
    dataSource.setUser(user);
    dataSource.setPassword(password);
    return dataSource;
  }
}
