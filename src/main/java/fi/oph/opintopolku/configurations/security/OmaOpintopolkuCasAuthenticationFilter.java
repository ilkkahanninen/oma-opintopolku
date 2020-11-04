package fi.oph.opintopolku.configurations.security;

import fi.vm.sade.java_utils.security.OpintopolkuCasAuthenticationFilter;
import fi.vm.sade.java_utils.security.OpintopolkuServiceAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.ServiceProperties;

public class OmaOpintopolkuCasAuthenticationFilter extends OpintopolkuCasAuthenticationFilter {
    @Autowired
    public OmaOpintopolkuCasAuthenticationFilter(ServiceProperties serviceProperties) {
        super(serviceProperties);
        setAuthenticationDetailsSource(new OmaOpintopolkuServiceAuthenticationDetailsSource(serviceProperties));
    }
}
