package fi.oph.opintopolku.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

import static java.util.Collections.emptyList;

public class OmaopintopolkuUserDetailsServiceImpl implements UserDetailsService {

    //public OphOppijaUserInfoServiceImpl() {
    //}

        @Override
        public UserDetails loadUserByUsername(String hetu) throws UsernameNotFoundException {
            User user = new User(getHetu(hetu), "", emptyList());
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

    private static final class UserDetailsImpl implements UserDetails {

//        private Collection<fi.vm.sade.javautils .oppijanumerorekistericlient.OphOppijaUserInfoServiceImpl.GrantedAuthorityImpl> authorities;
        private String password;
        private String username;
        private String hetu;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;

        private UserDetailsImpl(String hetu) {
            this.hetu = hetu;
        }

        @Override
        public Collection<GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return null;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        @Override
        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

    }

    private static final class GrantedAuthorityImpl implements GrantedAuthority {

        private String authority;

        @Override
        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

    }

}

