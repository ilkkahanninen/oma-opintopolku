package fi.oph.opintopolku.configurations;

import fi.oph.opintopolku.configurations.properties.OppijanumerorekisteriProperties;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import fi.vm.sade.properties.OphProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OnrClientConfiguration {

    private static final String CALLER_ID = "1.2.246.562.10.00000000001.henkilo-ui";

    @Bean
    @Primary
    public OphHttpClient ophHttpClient() {
        return new OphHttpClient.Builder(CALLER_ID)
            .build();
    }

    @Bean
    public OphHttpClient ophHttpClientOppijanumerorekisteri(OphProperties ophProperties, OppijanumerorekisteriProperties oppijanumerorekisteriProperties) {
        CasAuthenticator casAuthenticator = new CasAuthenticator.Builder()
            .username(oppijanumerorekisteriProperties.getUsername())
            .password(oppijanumerorekisteriProperties.getPassword())
            .webCasUrl(ophProperties.url("cas-oppija.url"))
            .casServiceUrl(ophProperties.url("oppijanumerorekisteri-service.security-check"))
            .build();
        return new OphHttpClient.Builder(ConfigEnums.CALLER_ID.value())
            .authenticator(casAuthenticator)
            .build();
    }

}
