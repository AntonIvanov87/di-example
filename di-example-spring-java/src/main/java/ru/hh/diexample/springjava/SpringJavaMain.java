package ru.hh.diexample.springjava;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.hh.diexample.users.UserDAO;

import static ru.hh.diexample.users.UserDAO.play;

public final class SpringJavaMain {

  public static void main(final String... args) {

    final ApplicationContext applicationContext = createApplicationContext();  // create graph of beans

    final UserDAO userDAO = applicationContext.getBean(UserDAO.class);  // get bean from the graph

    play(userDAO);  // use it
  }

  // public for benchmarks
  public static ApplicationContext createApplicationContext() {
    return new AnnotationConfigApplicationContext(SpringCommonConfig.class, SpringProdOnlyConfig.class);
  }

  private SpringJavaMain() {
  }
}
