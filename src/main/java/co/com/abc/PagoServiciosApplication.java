package co.com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PagoServiciosApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/pagoService/");
		SpringApplication.run(PagoServiciosApplication.class, args);
	}

}
