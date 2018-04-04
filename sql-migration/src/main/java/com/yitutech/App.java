package com.yitutech;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {

    public static void main( String[] args ) {

        String dbURI = System.getenv("db.uri");
        String dbUsername = System.getenv("db.username");
        String dbPassword = System.getenv("db.password");

        log.info("dbURI: " + dbURI + ", dbUsername: " + dbUsername + ", dbPassword: " + dbPassword);
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource(dbURI, dbUsername, dbPassword);

        // Start the migration
        flyway.migrate();
    }
}
