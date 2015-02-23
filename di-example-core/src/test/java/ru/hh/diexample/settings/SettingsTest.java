package ru.hh.diexample.settings;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SettingsTest {

  @Test(expected = ConfigException.Missing.class)
  public void shouldThrowConfigExceptionMissingIfDatabaseSectionNotFound() throws Exception {

    final Config config = ConfigFactory.parseResourcesAnySyntax("missing.conf");
    assertFalse(config.hasPath("database"));

    new Settings(config).databaseUrl();
  }

  @Test(expected = ConfigException.Missing.class)
  public void shouldThrowConfigExceptionMissingIfDatabasePasswordNotFound() throws Exception {

    final Config config = ConfigFactory.parseResourcesAnySyntax("ru/hh/diexample/settings/missingDatabasePassword.conf");
    assertTrue(config.hasPath("database"));

    new Settings(config).databasePassword();
  }

  @Test
  public void shouldReturnSettings() throws Exception {

    final Config config = ConfigFactory.load("ru/hh/diexample/settings/application.conf");

    final Settings settings = new Settings(config);

    assertEquals("some url", settings.databaseUrl());
    assertEquals("some user", settings.databaseUser());
    assertEquals("some password", settings.databasePassword());
  }
}
