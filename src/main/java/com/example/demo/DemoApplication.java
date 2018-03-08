package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.entities.Humain;

@ComponentScan(basePackages = {"com.example.demo", "org.csid.demo"})
@EnableAutoConfiguration(exclude = H2ConsoleAutoConfiguration.class)
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private A a;

	@Autowired
	private B b;

	@Value("${config.actif}")
	private boolean actif;

	public static void main(final String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Configuration
	public class FileDataSourceConfig {

		@Bean(name="dataSource")
		@Conditional(value = Dev.class)
		public DataSource getDevDataSource() {
			return (DataSource) new Dev();
		}

		@Bean(name="dataSource")
		@Conditional(value = Prod.class)
		public DataSource getProdDataSource() {
			return (DataSource) new Prod();
		}
	}

	@Component
	class A {
		@Autowired
		private B b;
	}

	@Component
	class B {
		public B() {
			System.out.println("ctor B");
		}

		public String foo() {
			return "foo";
		}
	}

	@Bean
	Humain getHumain() {
		return new Humain("d");
	}

	@Override
	public void run(final String... args) throws Exception {
		System.out.println("A" + a);
		System.out.println("B" + b);
		System.out.println(actif);

		System.out.println(getHumain());

	}
}
