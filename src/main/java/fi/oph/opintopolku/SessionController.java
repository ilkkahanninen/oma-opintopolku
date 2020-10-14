package fi.oph.opintopolku;

import lombok.val;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.joda.time.LocalDate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/session")
public class SessionController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping
//    public User getSession(@RequestParam(value = "ticket", required = false) String ticket) {
    public User getSession() {

        //private static Optional<Authentication> getAuthentication() {
        //    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        //}
        // TODO test start
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CasAuthenticationToken casAuthenticationToken = (CasAuthenticationToken) authentication;
        AttributePrincipal principal = casAuthenticationToken.getAssertion().getPrincipal();
        Map attributes = principal.getAttributes();
        String personOid = (String) attributes.getOrDefault("personOid", "NOT_FOUND");
        //TODO test end

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = new User();
        //val userName = getAuthentication().flatMap(authentication -> Optional.ofNullable(authentication.getName()));
        user.setName("testi testiajaja");
        //String displayName = ShibbolethUtils.parseDisplayName(etunimet, sukunimi);
        //user.setName(displayName);

       // LocalDate bd = ShibbolethUtils.parseDateFromHetu(hetu);
       // user.setBirthDay(bd);

        return user;
    }

}
