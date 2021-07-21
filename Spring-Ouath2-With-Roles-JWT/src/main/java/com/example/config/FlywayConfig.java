package com.example.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

	@Bean
	public Flyway flyway(@Autowired DataSource dataSource) {
		ClassicConfiguration configuration = new ClassicConfiguration();
		configuration.setDataSource(dataSource);
		configuration.setBaselineVersionAsString("0");
		configuration.setLocations(new Location("db"));
		configuration.setBaselineVersionAsString("0");
		configuration.setBaselineOnMigrate(true);
		Flyway flyway = new Flyway(configuration);
		flyway.migrate();
		return flyway;
	}

}
