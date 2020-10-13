package fi.oph.opintopolku.configurations;

import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import fi.vm.sade.properties.OphProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class OnrClientConfiguration {

    @Bean
    @Primary
    public OphHttpClient ophHttpClient() {
        return new OphHttpClient.Builder(ConfigEnums.CALLER_ID.value())
            .build();
    }

    @Bean
    @Qualifier("authentication")
    public OphHttpClient ophHttpClientOppijanumerorekisteri(OphProperties ophProperties, Environment environment) {
        CasAuthenticator casAuthenticator = new CasAuthenticator.Builder()
            .username(environment.getRequiredProperty("authentication.oppijanumerorekisteri.username"))
            .password(environment.getRequiredProperty("authentication.oppijanumerorekisteri.password"))
            .webCasUrl(ophProperties.url("cas-oppija.url"))
            .casServiceUrl(ophProperties.url("oppijanumerorekisteri-service.security-check"))
            .build();
        return new OphHttpClient.Builder(ConfigEnums.CALLER_ID.value())
            .authenticator(casAuthenticator)
            .build();
    }

}
