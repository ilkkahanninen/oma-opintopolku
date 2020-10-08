package fi.oph.opintopolku.configurations.properties;

import fi.vm.sade.properties.OphProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class UrlConfiguration extends OphProperties {

    @Autowired
    public UrlConfiguration(Environment environment) {
        addFiles("/oma-opintopolku-oph.properties");
        addOverride("host-cas", environment.getRequiredProperty("host.host-cas"));
        addOverride("host-oppija", environment.getRequiredProperty("host.host-oppija"));
        addOverride("host-virkailija", environment.getRequiredProperty("host.host-virkailija"));
    }
}
