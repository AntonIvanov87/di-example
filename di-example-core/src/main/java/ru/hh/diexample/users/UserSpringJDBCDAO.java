package ru.hh.diexample.users;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Named  // javax version of spring @Component
@Singleton  // for guice: guice default is prototype, spring default is singleton
public class UserSpringJDBCDAO implements UserDAO {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  @Inject  // pick this constructor, find JdbcTemplate bean and inject it here
  public UserSpringJDBCDAO(final JdbcTemplate jdbcTemplate) {

    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("users")
            .usingGeneratedKeyColumns("user_id");
  }

  @Override
  public void insert(final User newUser) {

    if (newUser.getId() != null) {
      throw new IllegalArgumentException(newUser + " already has id");
    }

    final Map<String, Object> params = new HashMap<>();
    params.put("first_name", newUser.getFirstName());
    params.put("last_name", newUser.getLastName());

    final int id = simpleJdbcInsert.executeAndReturnKey(params).intValue();

    newUser.setId(id);
  }

  @Override
  public Optional<User> get(final int userId) {

    final String query = "SELECT user_id, first_name, last_name FROM users WHERE user_id = :user_id";

    final Map<String, Object> params = new HashMap<>();
    params.put("user_id", userId);

    final User user;
    try {
      user = namedParameterJdbcTemplate.queryForObject(query, params, rowToUser);
    } catch (EmptyResultDataAccessException ignored) {
      return Optional.empty();
    }
    return Optional.of(user);
  }

  @Override
  public void update(final User user) {

    if (user.getId() == null) {
      throw new IllegalArgumentException(user + " does not have id");
    }

    final String query = "UPDATE users SET first_name = :first_name, last_name = :last_name WHERE user_id = :user_id";

    final Map<String, Object> params = new HashMap<>();
    params.put("first_name", user.getFirstName());
    params.put("last_name", user.getLastName());
    params.put("user_id", user.getId());

    namedParameterJdbcTemplate.update(query, params);
  }

  @Override
  public void delete(final int userId) {

    final String query = "DELETE FROM users WHERE user_id = :user_id";

    final Map<String, Object> params = new HashMap<>();
    params.put("user_id", userId);

    namedParameterJdbcTemplate.update(query, params);
  }

  private static final RowMapper<User> rowToUser = (resultSet, rowNum) ->
          User.createExisting(
                  resultSet.getInt("user_id"),
                  resultSet.getString("first_name"),
                  resultSet.getString("last_name")
          );
}
