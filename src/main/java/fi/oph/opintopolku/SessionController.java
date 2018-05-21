package fi.oph.opintopolku;

import lombok.val;
import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

    @GetMapping
    public User getSession(@RequestHeader(value = "nationalidentificationnumber", required = false) String hetu,
                           @RequestHeader(value = "firstname", required = false) String etunimet,
                           @RequestHeader(value = "sn", required = false) String sukunimi) {
        // Dekoodataan etunimet ja sukunimi manuaalisesti, koska shibboleth välittää ASCII-enkoodatut request headerit UTF-8 -merkistössä
        Charset windows1252 = Charset.forName("Windows-1252");
        Charset utf8 = Charset.forName("UTF-8");

        val user = new User();
        String name = "";

        if (etunimet != null) {
            etunimet = new String(etunimet.getBytes(windows1252), utf8);
            name += etunimet;
        }

        if (sukunimi != null) {
            sukunimi = new String(sukunimi.getBytes(windows1252), utf8);
            name += sukunimi;
        }
        user.setName(name);

        if (hetu != null) {
            LocalDate bd = ShibbolethUtils.parseDateFromHetu(hetu);
            user.setBirthDay(bd);
        }
        return user;
    }

    @GetMapping
    @RequestMapping(value = "/clear")
    public void clearSession() {
        // ??
    }

}
