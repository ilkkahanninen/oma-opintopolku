package fi.oph.opintopolku.configurations.security;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

public class OmaopintopolkuCasAuthenticationEntryPoint extends CasAuthenticationEntryPoint {
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
        String serviceUrl = super.getServiceProperties().getService();

        try {
            serviceUrl = getLocalizedServiceUrl(request.getRequestURI());
        } catch (URISyntaxException e) {
            //TODO: should we continue with default serviceUrl?
            e.printStackTrace();
        }

        return CommonUtils.constructServiceUrl(null, response,
            serviceUrl, null,
            super.getServiceProperties().getArtifactParameter(),
            super.getEncodeServiceUrlWithSessionId());
    }

    private String getLocalizedServiceUrl(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String host = uri.getHost();

        String initialUri = super.getServiceProperties().getService();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(initialUri);
        return builder.host(host).toUriString();
    }


}


