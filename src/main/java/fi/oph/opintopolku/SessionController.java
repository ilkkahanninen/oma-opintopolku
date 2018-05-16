package fi.oph.opintopolku;

import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/session")
public class SessionController {

    @GetMapping
    public User getSession() {

        val user = new User();
        user.setName("Erkki Esimerkki");
        return user;

    }

    @GetMapping
    @RequestMapping(value = "/clear")
    public void clearSession() {

        // ??

    }

}
