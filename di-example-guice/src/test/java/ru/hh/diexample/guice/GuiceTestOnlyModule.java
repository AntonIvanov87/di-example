package ru.hh.diexample.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.inject.Singleton;
import javax.sql.DataSource;

/** guice module that declares beans only for testing purposes */
class GuiceTestOnlyModule extends AbstractModule {

  @Override
  protected void configure() {
  }

  @Provides
  @Singleton
  static DataSource provideEmbeddedDatabase() {

    final EmbeddedDatabase embeddedDatabase = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("create-tables.sql")
            .build();
    closeEmbeddedDatabaseOnShutdown(embeddedDatabase);
    return embeddedDatabase;
  }

  private static void closeEmbeddedDatabaseOnShutdown(final EmbeddedDatabase embeddedDatabase) {
    Runtime.getRuntime().addShutdownHook(new Thread(embeddedDatabase::shutdown));
  }
}
