package navercorp.com.andy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AndyApplication {

	public static void main(String[] args) {
		System.setProperty("java.security.egd", "file:/dev/./urandom");
		SpringApplication.run(AndyApplication.class, args);
	}

}
