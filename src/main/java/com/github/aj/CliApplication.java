package com.github.aj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@SpringBootApplication
public class CliApplication {

	public static void main(String[] args) {
		// AnsiConsole.systemInstall();
		SpringApplication.run(CliApplication.class, args);
	}

}
