package fi.oph.opintopolku.services.impl;

import com.google.gson.Gson;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.OphHttpRequest;
import fi.vm.sade.properties.OphProperties;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

import static java.util.Collections.emptyList;

public class OmaopintopolkuUserDetailsServiceImpl implements UserDetailsService {
    private final OphHttpClient httpClient;
    private final OphProperties ophProperties;

    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMYY");

    public OmaopintopolkuUserDetailsServiceImpl(String urlVirkailija, OphHttpClient httpClient) {
        this.httpClient = httpClient;
        this.ophProperties = new OphProperties("/onrclient-oph.properties")
            .addOverride("url-virkailija", urlVirkailija);
    }

    @Override
    public UserDetails loadUserByUsername(String suomiFiHetu) throws UsernameNotFoundException {
        String hetu = getHetu(suomiFiHetu);

        OphHttpRequest request = OphHttpRequest.Builder
            .get(ophProperties.url("oppijanumerorekisteri-service.userInfo.byHetu", hetu))
            .build();

        OnrResponse onrResponse = httpClient.<OnrResponse>execute(request)
            .expectedStatus(200).mapWith(response -> {
                InputStream responseStream = new ByteArrayInputStream(response.getBytes());
                return new Gson().fromJson(new InputStreamReader(responseStream), OnrResponse.class);
            }).orElseThrow(() -> new UsernameNotFoundException(String.format("Käyttäjää ei löytynyt henkilötunnuksella '%s'", hetu)));

        OmaOpintopolkuUser user = new OmaOpintopolkuUser(onrResponse.getOidHenkilo(), "", true, true,
            true, true, emptyList(), onrResponse.getKutsumaNimi() + onrResponse.getSukunimi(), getBirthDay(hetu));
        user.eraseCredentials();
        return user;
    }

    private String getHetu(String suomiFiHetu) {
        if (suomiFiHetu.length() > 11 && suomiFiHetu.contains("#")) {
            String parts[] = suomiFiHetu.split("#");
            return parts[1];
        }
        return suomiFiHetu;
    }


    private LocalDate getBirthDay(String hetu) {
        if (hetu != null) {
            return formatter.parseLocalDate(hetu.substring(0, 6));
        }
        return null;
    }

    @Getter
    @Setter
    private class OnrResponse {
        String etunimet;
        String hetu;
        String kutsumaNimi;
        String oidHenkilo;
        String sukunimi;
    }

    private static final class OmaOpintopolkuUser extends User {
        public OmaOpintopolkuUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                                  boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String name, LocalDate birthDay) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.name = name;
            this.birthDay = birthDay;
        }
        @Setter
        @Getter
        public String name;
        public LocalDate birthDay;
    }
}

