package fi.oph.opintopolku.configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OnrClientConfiguration {

//    @Bean
//    @Primary
//    public OphHttpClient ophHttpClient() {
//        return new OphHttpClient.Builder(ConfigEnums.CALLER_ID.value())
//            .build();
//    }
//
//    @Bean
//    @Qualifier("authentication")
//    public OphHttpClient ophHttpClientOppijanumerorekisteri(OphProperties ophProperties, Environment environment) {
//        CasAuthenticator casAuthenticator = new CasAuthenticator.Builder()
//            .username(environment.getRequiredProperty("authentication.oppijanumerorekisteri.username"))
//            .password(environment.getRequiredProperty("authentication.oppijanumerorekisteri.password"))
//            .webCasUrl(ophProperties.url("cas.url"))
//            .casServiceUrl(ophProperties.url("oppijanumerorekisteri-service.security-check"))
//            .build();
//        return new OphHttpClient.Builder(ConfigEnums.CALLER_ID.value())
//            .authenticator(casAuthenticator)
//            .build();
//    }

}
