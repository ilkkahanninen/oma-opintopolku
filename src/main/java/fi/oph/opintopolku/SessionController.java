package fi.oph.opintopolku;

import lombok.val;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping(value = "/session")
public class SessionController {
    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.YY");

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public User getSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CasAuthenticationToken casAuthenticationToken = (CasAuthenticationToken) authentication;
        AttributePrincipal principal = casAuthenticationToken.getAssertion().getPrincipal();
        Map attributes = principal.getAttributes();

        val user = new User();

        user.setName((String) attributes.getOrDefault("displayName", "NOT_FOUND"));
       // LocalDate bd = parseDateFromHetu((String) attributes.getOrDefault("nationalIdentificationNumber", ""));
        user.setBirthDay(parseDateFromHetu((String) attributes.getOrDefault("nationalIdentificationNumber", "")));
        user.setPersonOid((String) attributes.getOrDefault("personOid", "NOT_FOUND"));
        user.setHetu((String) attributes.getOrDefault("nationalIdentificationNumber", "NOT_FOUND"));
        return user;
    }

    private static String parseDateFromHetu(String hetu) {
        if (hetu != null) {
            return formatter.parseLocalDate(hetu.substring(0, 6)).toString();
        }
        return null;
    }
}
