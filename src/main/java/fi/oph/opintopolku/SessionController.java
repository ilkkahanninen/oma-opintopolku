package fi.oph.opintopolku;

import lombok.val;
import org.joda.time.LocalDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/initsession")
public class SessionController {

    @GetMapping
    public User getSession(@RequestParam(value = "ticket", required = true) String ticket) {

        //private static Optional<Authentication> getAuthentication() {
        //    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        //}
        //TODO - miten tiketti validoidaan? mistä haetaan nimi ja hetu? authentication?

        val user = new User();
        //val userName = getAuthentication().flatMap(authentication -> Optional.ofNullable(authentication.getName()));
        user.setName("Nakki Nakuttaja");
        //String displayName = ShibbolethUtils.parseDisplayName(etunimet, sukunimi);
        //user.setName(displayName);

       // LocalDate bd = ShibbolethUtils.parseDateFromHetu(hetu);
       // user.setBirthDay(bd);

        return user;
    }

}
