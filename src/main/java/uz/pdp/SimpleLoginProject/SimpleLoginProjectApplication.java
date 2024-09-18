package uz.pdp.SimpleLoginProject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import uz.pdp.SimpleLoginProject.utils.SecurityUtils;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleLoginProjectApplication {

    private final SecurityUtils securityUtils;

    public SimpleLoginProjectApplication(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleLoginProjectApplication.class, args);
    }

//    @Bean
    public CommandLineRunner insertFakeData(DataSource dataSource) {
        return args -> {
            Resource resource = new ClassPathResource("fakeData.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        };
    }

    @Bean
    public AuditorAware<Integer> auditorAware() {
        return () -> Optional.of(securityUtils.getUser());
    }
}
