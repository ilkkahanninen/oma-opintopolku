package fi.oph.opintopolku;

import lombok.val;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


@RestController
public class SessionController {
    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMYY");

    @RequestMapping(value = "/session")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public User getSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CasAuthenticationToken casAuthenticationToken = (CasAuthenticationToken) authentication;
        AttributePrincipal principal = casAuthenticationToken.getAssertion().getPrincipal();
        Map attributes = principal.getAttributes();

        val user = new User();

        user.setName((String) attributes.getOrDefault("displayName", "NOT_FOUND"));
        user.setBirthDay(parseDateStringFromHetu((String) attributes.getOrDefault("nationalIdentificationNumber", "")));
        user.setPersonOid((String) attributes.getOrDefault("personOid", "NOT_FOUND"));
        user.setHetu((String) attributes.getOrDefault("nationalIdentificationNumber", "NOT_FOUND"));
        return user;
    }

    @RequestMapping(value = "/authenticate")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public RedirectView authenticate() {
        return new RedirectView("/oma-opintopolku");
    }

    @RequestMapping(value = "/logout?service={service}")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public RedirectView logout(@PathVariable String service) {
        return new RedirectView("/cas-oppija/logout?service=" + service);
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
