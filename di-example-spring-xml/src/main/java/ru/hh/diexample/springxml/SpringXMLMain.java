package ru.hh.diexample.springxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.hh.diexample.users.UserDAO;

public final class SpringXMLMain {

  public static void main(final String... args) {

    final ApplicationContext applicationContext = createApplicationContext();  // create graph of beans

    final UserDAO userDAO = applicationContext.getBean(UserDAO.class);  // get bean from the graph

    UserDAO.play(userDAO);  // use it
  }

  // public for benchmarks
  public static ApplicationContext createApplicationContext() {
    return new ClassPathXmlApplicationContext("/ru/hh/diexample/springxml/prod-beans.xml");
  }

  private SpringXMLMain() {
  }
}
