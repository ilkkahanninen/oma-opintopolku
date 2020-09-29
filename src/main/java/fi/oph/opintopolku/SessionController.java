package fi.oph.opintopolku;

import lombok.val;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/initsession")
public class SessionController {

    @GetMapping
    public User getSession(@RequestHeader(value = "ticket", required = true) String ticket) {

        val user = new User();
        user.setBirthDay(new LocalDate(LocalDate.now()));
        user.setName("Nakki Nakuttaja");
        //String displayName = ShibbolethUtils.parseDisplayName(etunimet, sukunimi);
        //user.setName(displayName);

       // LocalDate bd = ShibbolethUtils.parseDateFromHetu(hetu);
       // user.setBirthDay(bd);

        return user;
    }

}
