package fi.oph.opintopolku;

import lombok.val;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class SessionController {
    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMYY");
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @RequestMapping(value = "/session")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public User getSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CasAuthenticationToken casAuthenticationToken = (CasAuthenticationToken) authentication;
        AttributePrincipal principal = casAuthenticationToken.getAssertion().getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();

        val user = new User();

        String attributesAsString = attributes.keySet().stream().map(k -> k + ":" + attributes.get(k))
            .collect(Collectors.joining(", ", "{", "}" ));
        logger.info("Setting user properties, data: {}", attributesAsString);

        boolean isUsingValtuudet = isUsingValtuudet(attributes);
        String displayName = !isUsingValtuudet
            ? (String) attributes.getOrDefault("displayName", "NOT_FOUND")
            : (String) attributes.getOrDefault("impersonatorDisplayName", "NOT_FOUND");

        user.setName(displayName);
        user.setBirthDay(parseDateStringFromHetu((String) attributes.getOrDefault("nationalIdentificationNumber", "")));
        user.setPersonOid((String) attributes.getOrDefault("personOid", "NOT_FOUND"));
        user.setHetu((String) attributes.getOrDefault("nationalIdentificationNumber", "NOT_FOUND"));
        user.setUsingValtuudet(isUsingValtuudet);

        logger.info("Returning user {}", user.toString());
        return user;
    }

    @RequestMapping(value = "/authenticate")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public RedirectView authenticate() {
        return new RedirectView("/oma-opintopolku/");
    }

    private static boolean isUsingValtuudet(Map<String, Object> attributes) {
        return attributes.containsKey("impersonatorNationalIdentificationNumber")
            || attributes.containsKey("impersonatorDisplayName");
    }

    private static String parseDateStringFromHetu(String hetu) {
        if (hetu != null) {
            Locale locale = new Locale("fi","fi");
            DateTime dt = formatter.parseDateTime(hetu.substring(0, 6));
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            Date date = dt.toDate();
            return df.format(date);
        }
        return "";
    }
}
