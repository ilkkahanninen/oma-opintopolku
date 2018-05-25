package fi.oph.opintopolku;

import lombok.val;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

    @GetMapping
    public User getSession(@RequestHeader(value = "nationalidentificationnumber", required = false) String hetu,
                           @RequestHeader(value = "firstname", required = false) String etunimet,
                           @RequestHeader(value = "sn", required = false) String sukunimi) {
        val user = new User();
        String displayName = ShibbolethUtils.parseDisplayName(etunimet, sukunimi);
        user.setName(displayName);

        LocalDate bd = ShibbolethUtils.parseDateFromHetu(hetu);
        user.setBirthDay(bd);

        return user;
    }

}
