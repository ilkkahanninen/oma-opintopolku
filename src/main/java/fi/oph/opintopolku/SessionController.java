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

        val user = createUser(attributes);
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
        String impersonatorHetu = (String) attributes.getOrDefault("impersonatorNationalIdentificationNumber", "");
        String impersonatorName = (String) attributes.getOrDefault("impersonatorDisplayName", "");
        return !impersonatorHetu.isEmpty() || !impersonatorName.isEmpty();
    }

    private static User createUser(Map<String, Object> attributes) {
        val user = new User();

        String attributesAsString = attributes.keySet().stream().map(k -> k + ":" + attributes.get(k))
            .collect(Collectors.joining(", ", "{", "}" ));
        logger.info("Setting user properties, data: {}", attributesAsString);

        boolean isUsingValtuudet = isUsingValtuudet(attributes);
        String displayName = !isUsingValtuudet
            ? (String) attributes.getOrDefault("displayName", "NOT_FOUND")
            : (String) attributes.getOrDefault("impersonatorDisplayName", "NOT_FOUND");
        String birthDay = !isUsingValtuudet
            ? parseDateStringFromHetu((String) attributes.getOrDefault("nationalIdentificationNumber", ""))
            : parseDateStringFromHetu((String) attributes.getOrDefault("impersonatorNationalIdentificationNumber", ""));
        String personOid = !isUsingValtuudet
            ? (String) attributes.getOrDefault("personOid", "NOT_FOUND")
            : (String) attributes.getOrDefault("impersonatorPersonOid", "NOT_FOUND");
        String hetu = !isUsingValtuudet
            ? (String) attributes.getOrDefault("nationalIdentificationNumber", "NOT_FOUND")
            : (String) attributes.getOrDefault("impersonatorNationalIdentificationNumber", "NOT_FOUND");

        user.setName(displayName);
        user.setBirthDay(birthDay);
        user.setPersonOid(personOid);
        user.setHetu(hetu);
        user.setUsingValtuudet(isUsingValtuudet);

        return user;
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
