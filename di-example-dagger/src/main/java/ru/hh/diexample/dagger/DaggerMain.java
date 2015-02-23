package ru.hh.diexample.dagger;

import dagger.ObjectGraph;
import ru.hh.diexample.users.UserDAO;

import static ru.hh.diexample.users.UserDAO.play;

public final class DaggerMain {

  public static void main(final String... args) {

    final ObjectGraph objectGraph = createObjectGraph();  // create graph of beans

    final UserDAO userDAO = objectGraph.get(UserDAO.class);  // get bean from the graph

    play(userDAO);  // use it
  }

  // public for benchmarks
  public static ObjectGraph createObjectGraph() {
    return ObjectGraph.create(new DaggerProdModule());
  }

  private DaggerMain() {
  }
}
