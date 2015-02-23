package ru.hh.diexample.springannotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.hh.diexample.users.UserDAO;

import static ru.hh.diexample.users.UserDAO.play;

public final class SpringAnnotationsMain {

  public static void main(final String... args) {

    final ApplicationContext applicationContext = createApplicationContext();  // create graph of beans

    final UserDAO userDAO = applicationContext.getBean(UserDAO.class);  // get bean from the graph

    play(userDAO);  // use it
  }

  // public for benchmarks
  public static ApplicationContext createApplicationContext() {
    return new ClassPathXmlApplicationContext("/ru/hh/diexample/springannotations/prod-beans.xml");
  }

  private SpringAnnotationsMain() {
  }
}
