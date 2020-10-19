package fi.oph.opintopolku.configurations.security;

import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.authentication.ServiceAuthenticationDetails;
import org.springframework.security.cas.web.authentication.ServiceAuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class OmaopintopolkuServiceAuthenticationDetailsSource extends ServiceAuthenticationDetailsSource {
    private final ServiceProperties serviceProperties;

    public OmaopintopolkuServiceAuthenticationDetailsSource(ServiceProperties serviceProperties) {
        super(serviceProperties);
        this.serviceProperties = serviceProperties;
    }

    @Override
    public ServiceAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new fi.oph.opintopolku.configurations.security.OmaopintopolkuServiceAuthenticationDetailsSource.OmaopintopolkuAuthenticationDetails(request, serviceProperties.getService());
    }

    public static class OmaopintopolkuAuthenticationDetails extends WebAuthenticationDetails implements ServiceAuthenticationDetails{
        private final String serviceUrl;

        public OmaopintopolkuAuthenticationDetails(HttpServletRequest request, String serviceUrl) {
            super(request);
            this.serviceUrl = serviceUrl;
        }

        @Override
        public String getServiceUrl() {
            return serviceUrl;
        }
    }
}

