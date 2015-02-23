package ru.hh.diexample.springxml;

import org.junit.After;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.jdbc.JdbcTestUtils;

public class SpringXMLTestBase {

  private static final ConfigurableApplicationContext applicationContext =
          new ClassPathXmlApplicationContext("/ru/hh/diexample/springxml/test-beans.xml");

  static EmbeddedDatabase createEmbeddedDatabase() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("create-tables.sql")
            .build();
  }

  static {
    applicationContext.registerShutdownHook();
  }

  protected static <T> T getBean(Class<T> requiredType) {
    return applicationContext.getBean(requiredType);
  }

  @After
  public void tearDown() throws Exception {
    JdbcTestUtils.deleteFromTables(getBean(JdbcTemplate.class), "users");
  }
}
