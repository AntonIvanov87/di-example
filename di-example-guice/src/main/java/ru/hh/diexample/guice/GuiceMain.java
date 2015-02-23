package ru.hh.diexample.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.hh.diexample.users.UserDAO;

import static ru.hh.diexample.users.UserDAO.play;

public final class GuiceMain {

  public static void main(final String... args) {

    final Injector injector = createInjector();  // create graph of beans

    final UserDAO userDAO = injector.getInstance(UserDAO.class);  // get bean from the graph

    play(userDAO);  // use it
  }

  // public for benchmarks
  public static Injector createInjector() {
    return Guice.createInjector(new GuiceCommonModule(), new GuiceProdOnlyModule());
  }

  private GuiceMain() {
  }
}
