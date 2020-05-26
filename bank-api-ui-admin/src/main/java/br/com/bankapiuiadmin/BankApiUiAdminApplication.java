package br.com.bankapiuiadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class BankApiUiAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApiUiAdminApplication.class, args);
	}

}
