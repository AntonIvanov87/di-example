package ru.hh.diexample.guice;

import com.google.inject.Injector;
import org.junit.After;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import static com.google.inject.Guice.createInjector;

public class GuiceTestBase {

  private static final Injector injector = createInjector(new GuiceCommonModule(), new GuiceTestOnlyModule());

  protected static <T> T getInstance(Class<T> type) {
    return injector.getInstance(type);
  }

  @After
  public void tearDownGuiceTestBase() throws Exception {
    JdbcTestUtils.deleteFromTables(getInstance(JdbcTemplate.class), "users");
  }
}
