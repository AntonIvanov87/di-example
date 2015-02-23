package ru.hh.diexample.springjava;

import org.junit.After;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

public class SpringJavaTestBase {

  private static final ConfigurableApplicationContext applicationContext =
          new AnnotationConfigApplicationContext(SpringCommonConfig.class, SpringTestOnlyConfig.class);
  static {
    applicationContext.registerShutdownHook();
  }

  protected static <T> T getBean(Class<T> requiredType) {
    return applicationContext.getBean(requiredType);
  }

  @After
  public void tearDownSpringJavaTestBase() throws Exception {
    JdbcTestUtils.deleteFromTables(getBean(JdbcTemplate.class), "users");
  }
}
