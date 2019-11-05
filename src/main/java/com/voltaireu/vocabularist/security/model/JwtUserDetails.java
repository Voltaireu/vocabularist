package com.voltaireu.vocabularist.security.model;

import com.voltaireu.vocabularist.language.LanguageName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUserDetails implements UserDetails {

    private static final long serialVersionUID = 2778612926260733059L;

    private final Long id;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;
    private final boolean enabled;
    private final LanguageName nativeLanguage;

    public JwtUserDetails(
            Long id, Collection<? extends GrantedAuthority> authorities,
            String password,
            String username,
            Boolean enabled,
            LanguageName nativeLanguage) {
        this.id = id;
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.enabled = enabled;
        this.nativeLanguage = nativeLanguage;
    }

    public Long getId() {
        return id;
    }

    public LanguageName getNativeLanguage() {
        return nativeLanguage;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
