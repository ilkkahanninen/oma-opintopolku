package fi.oph.opintopolku.configurations.security;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OmaopintopolkuCasAuthenticationEntryPoint extends CasAuthenticationEntryPoint {
//    public OmaopintopolkuCasAuthenticationEntryPoint() {
//        super();
//    }
    /**
     * Constructs a new Service Url. The default implementation relies on the CAS client
     * to do the bulk of the work.
     * @param request the HttpServletRequest
     * @param response the HttpServlet Response
     * @return the constructed service url. CANNOT be NULL.
     */
    @Override
    protected String createServiceUrl(final HttpServletRequest request,
                                      final HttpServletResponse response) {
        return CommonUtils.constructServiceUrl(null, response,
            super.getServiceProperties().getService(), null,
            super.getServiceProperties().getArtifactParameter(),
            super.getEncodeServiceUrlWithSessionId());
    }
}
