package fi.oph.opintopolku.configurations.security;

import fi.oph.opintopolku.configurations.ConfigEnums;
import fi.oph.opintopolku.configurations.properties.CasOppijaProperties;
import fi.oph.opintopolku.configurations.properties.OppijanumerorekisteriProperties;
import fi.vm.sade.java_utils.security.OpintopolkuCasAuthenticationFilter;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import fi.vm.sade.javautils.oppijanumerorekistericlient.OphOppijaUserInfoServiceImpl;
import fi.vm.sade.properties.OphProperties;
import org.jasig.cas.client.session.SessionMappingStorage;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Profile("!dev")
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = false, prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private CasOppijaProperties casOppijaProperties;
    private OphProperties ophProperties;
    private OppijanumerorekisteriProperties oppijanumerorekisteriProperties;
    private Environment environment;
    private SessionMappingStorage sessionMappingStorage;
    private OphHttpClient ophHttpClient;

    @Autowired
    public SecurityConfiguration(CasOppijaProperties casOppijaProperties, OphProperties ophProperties, OppijanumerorekisteriProperties oppijanumerorekisteriProperties, Environment environment,
                                 SessionMappingStorage sessionMappingStorage) {
        this.casOppijaProperties = casOppijaProperties;
        this.ophProperties = ophProperties;
        this.oppijanumerorekisteriProperties = oppijanumerorekisteriProperties;
        this.environment = environment;
        this.sessionMappingStorage = sessionMappingStorage;

        CasAuthenticator casAuthenticator = new CasAuthenticator.Builder()
            .username(this.oppijanumerorekisteriProperties.getUsername())
            .password(this.oppijanumerorekisteriProperties.getPassword())
            .webCasUrl(this.casOppijaProperties.getUrl())
            .casServiceUrl(ophProperties.url("oppijanumerorekisteri-service.security-check"))
            .build();

        this.ophHttpClient = new OphHttpClient.Builder(ConfigEnums.CALLER_ID.value())
            .authenticator(casAuthenticator)
            .build();
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casOppijaProperties.getService() + "/session");
        serviceProperties.setSendRenew(casOppijaProperties.getSendRenew());
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    //
    // CAS authentication provider (authentication manager)
    //

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        //String host = "https://" + environment.getRequiredProperty("host.host-virkailija");
        String host = environment.getProperty("host.host-alb", "https://" + environment.getRequiredProperty("host.host-virkailija"));
        casAuthenticationProvider.setUserDetailsService(new OphOppijaUserInfoServiceImpl(host, ophHttpClient));
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(ticketValidator());
        casAuthenticationProvider.setKey(casOppijaProperties.getKey());
        return casAuthenticationProvider;
    }

    @Bean
    public TicketValidator ticketValidator() {
        Cas20ProxyTicketValidator ticketValidator = new Cas20ProxyTicketValidator(ophProperties.url("cas-oppija.url"));
        ticketValidator.setAcceptAnyProxy(true);
        return ticketValidator;
    }

    //
    // CAS filter
    //
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        OpintopolkuCasAuthenticationFilter casAuthenticationFilter = new OpintopolkuCasAuthenticationFilter(serviceProperties());
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
        return casAuthenticationFilter;
    }

    //
    // CAS single logout filter
    // requestSingleLogoutFilter is not configured because our users always sign out through CAS logout (using virkailija-raamit
    // logout button) when CAS calls this filter if user has ticket to this service.
    //
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(this.ophProperties.url("url-oppija"));
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        singleSignOutFilter.setSessionMappingStorage(sessionMappingStorage);
        return singleSignOutFilter;
    }

    //
    // CAS entry point
    //
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        //casAuthenticationEntryPoint.setLoginUrl(ophProperties.url("/cas-oppija/login?locale=FI&valtuudet=false&service=https://untuvaopintopolku.fi/oma-opintopolku/initsession"));
        //casAuthenticationEntryPoint.setLoginUrl("/cas-oppija/login?locale=FI&valtuudet=false&service=https://untuvaopintopolku.fi/oma-opintopolku/initsession");
        casAuthenticationEntryPoint.setLoginUrl(ophProperties.url("cas-oppija.login"));
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().disable()
            .csrf().disable()
            .authorizeRequests()
//            .antMatchers("/buildversion.txt").permitAll()
//            .antMatchers("/actuator/**").permitAll()
//            .antMatchers("/swagger-ui.html").permitAll()
//            .antMatchers("/swagger-resources/**").permitAll()
//            .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
//            .antMatchers("/v2/api-docs").permitAll()
            .antMatchers("/session").authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilter(casAuthenticationFilter())
            .exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint())
            .and()
            .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(casAuthenticationProvider());
    }


}
