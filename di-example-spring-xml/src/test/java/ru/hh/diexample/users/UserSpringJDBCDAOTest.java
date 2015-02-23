package ru.hh.diexample.users;

import org.junit.Test;
import ru.hh.diexample.springxml.SpringXMLTestBase;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserSpringJDBCDAOTest extends SpringXMLTestBase {

  private static final UserDAO userDAO = getBean(UserSpringJDBCDAO.class);

  @Test
  public void insertShouldInsertNewUserInDBAndAssignId() throws Exception {

    final User user1 = User.createNew("Ville", "Valo");
    assertNull(user1.getId());

    userDAO.insert(user1);

    assertNotNull(user1.getId());

    final User user2 = User.createNew("Martin", "Odersky");

    userDAO.insert(user2);

    assertNotNull(user2.getId());
    assertNotEquals(user1.getId(), user2.getId());
  }

  @Test
  public void getShouldReturnUser() throws Exception {

    final User user = User.createNew("Ville", "Valo");
    userDAO.insert(user);

    final Optional<User> userFromDB = userDAO.get(user.getId());

    assertEquals(user, userFromDB.get());
  }

  @Test
  public void getShouldReturnEmptyOptionalIfNoUserWithSuchId() throws Exception {

    final Optional<User> userFromDB = userDAO.get(666);

    assertFalse(userFromDB.isPresent());
  }

  @Test
  public void updateShouldUpdateUser() throws Exception {

    final User user = User.createNew("Ville", "Valo");
    userDAO.insert(user);

    user.setFirstName("Ivan");

    userDAO.update(user);

    final User userFromDB = userDAO.get(user.getId()).get();
    assertEquals(user, userFromDB);
  }

  @Test
  public void deleteShouldDeleteUserById() throws Exception {

    final User user1 = User.createNew("Ville", "Valo");
    userDAO.insert(user1);

    final User user2 = User.createNew("Martin", "Odersky");
    userDAO.insert(user2);

    userDAO.delete(user1.getId());

    assertFalse(userDAO.get(user1.getId()).isPresent());
    assertTrue(userDAO.get(user2.getId()).isPresent());
  }
}
