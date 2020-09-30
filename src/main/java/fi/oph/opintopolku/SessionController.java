package fi.oph.opintopolku;

import lombok.val;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/initsession")
public class SessionController {

    @GetMapping
    public User getSession(@RequestParam(value = "ticket", required = true) String ticket) {

        //TODO - miten tiketti validoidaan? hoituuko automaattisesti?

        val user = new User();
        user.setName("Nakki Nakuttaja");
        //String displayName = ShibbolethUtils.parseDisplayName(etunimet, sukunimi);
        //user.setName(displayName);

       // LocalDate bd = ShibbolethUtils.parseDateFromHetu(hetu);
       // user.setBirthDay(bd);

        return user;
    }

}
