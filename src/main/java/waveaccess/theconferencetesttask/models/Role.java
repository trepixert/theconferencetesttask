package waveaccess.theconferencetesttask.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    LISTENER,
    PRESENTER,
    ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
