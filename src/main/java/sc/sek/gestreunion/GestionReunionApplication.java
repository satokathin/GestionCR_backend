package sc.sek.gestreunion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GestionReunionApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionReunionApplication.class, args);
    }

}
