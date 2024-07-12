package ch.axa.its.emorganisationbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "EM-Organisation Backend", description = "This API saves all data to where someone works or worked at a specific game in the European Soccer Championship"))
@SpringBootApplication
public class EmOrganisationBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmOrganisationBackendApplication.class, args);
	}

}
