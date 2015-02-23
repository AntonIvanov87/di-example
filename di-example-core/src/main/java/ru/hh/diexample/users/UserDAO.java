package ru.hh.diexample.users;

import java.util.Optional;

public interface UserDAO {

  void insert(User newUser);

  Optional<User> get(int userId);

  void update(User user);

  void delete(int userId);

  static void play(final UserDAO userDAO) {

    final User user = User.createNew("Gordon", "Freeman");
    userDAO.insert(user);
    System.out.println("persisted " + user);

    user.setFirstName("Morgan");
    userDAO.update(user);
    System.out.println("updated user to " + user);

    userDAO.delete(user.getId());
    System.out.println("deleted " + user);

    final Optional<User> absentUser = userDAO.get(user.getId());
    System.out.println("tried to get user by " + user.getId() + " but got " + absentUser);
  }
}
