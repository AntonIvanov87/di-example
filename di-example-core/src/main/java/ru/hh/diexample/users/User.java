package ru.hh.diexample.users;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class User {

  private Integer id;
  private String firstName;
  private String lastName;

  public static User createNew(final String firstName, final String lastName) {
    return new User(null, firstName, lastName);
  }

  static User createExisting(final int id, final String firstName, final String lastName) {
    return new User(id, firstName, lastName);
  }

  private User(final Integer id, final String firstName, final String lastName) {
    this.id = id;
    this.firstName = requireNonNull(firstName);
    this.lastName = requireNonNull(lastName);
  }

  public Integer getId() {
    return id;
  }

  void setId(final int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(final Object that) {
    if (this == that) return true;
    if (that == null) return false;

    final User thatUser = (User) that;
    return Objects.equals(id, thatUser.id)
            && firstName.equals(thatUser.firstName)
            && lastName.equals(thatUser.lastName);
  }

  @Override
  public int hashCode() {
    return id == null ? 0 : id;
  }

  @Override
  public String toString() {
    return String.format(
            "%s{id=%d, firstName=%s, lastName=%s}",
            getClass().getSimpleName(), id, firstName, lastName
    );
  }
}
