package ru.hh.diexample.dagger;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dagger.Module;
import dagger.Provides;
import org.postgresql.ds.PGSimpleDataSource;
import ru.hh.diexample.settings.Settings;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

/** dagger module that declare beans only for production (not testing) purposes */
@Module(includes = DaggerCommonModule.class)
class DaggerProdModule {

  @Provides
  @Singleton
  Settings provideSettings() {
    final Config config = ConfigFactory.load();
    return new Settings(config);
  }

  @Provides
  @Singleton
  @Named("databaseUrl")
  String provideDatabaseUrl(final Settings settings) {
    return settings.databaseUrl();
  }

  @Provides
  @Singleton
  @Named("databaseUser")
  String provideDatabaseUser(final Settings settings) {
    return settings.databaseUser();
  }

  @Provides
  @Singleton
  @Named("databasePassword")
  String provideDatabasePassword(final Settings settings) {
    return settings.databasePassword();
  }

  @Provides
  @Singleton
  DataSource provideDataSource(
          @Named("databaseUrl") final String url,
          @Named("databaseUser") final String user,
          @Named("databasePassword") final String password) {

    final PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setUrl(url);
    dataSource.setUser(user);
    dataSource.setPassword(password);
    return dataSource;
  }
}
