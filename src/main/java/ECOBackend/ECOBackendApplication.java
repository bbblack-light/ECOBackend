package ECOBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECOBackendApplication {
	private static Initializer initializer;
	public ECOBackendApplication(Initializer initializer) {
		this.initializer = initializer;
	}

	public static void main(String[] args) {
		SpringApplication.run(ECOBackendApplication.class, args);
		Initializer.createAdmin();
	}

}
