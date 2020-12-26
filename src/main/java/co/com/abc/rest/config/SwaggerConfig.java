package co.com.abc.rest.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("co.com.abc.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("Pago de servicios", 
				"Permite pagar las facturas de los servicios con los cuales el banco tenga un convenio",
				"1.0",
				"Estar registrado",
				new Contact("Pepe", "www.abc.co.com", "micorreo@abc.co.com"),
				"",
				"", 
				Collections.emptyList());
	}
}
