package fi.oph.opintopolku.configurations.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "authentication.oppijanumerorekisteri")
public class OppijanumerorekisteriProperties {
    private String username;
    private String password;

    private Boolean noUpdateMode = false;
}
