package io.elice.shoppingmall.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class MyTokenAuthentication implements Authentication {
    private final String token;
    //
    @Setter
    private MyTokenPayload payload = new MyTokenPayload();
    @Setter
    private boolean authenticated = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return payload.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public MyTokenPayload getDetails() {
        return payload;
    }

    @Override
    public Long getPrincipal() {
        return payload.getUserId();
    }

    @Override
    public String getName() {
        return payload.getUsername();
    }

}