package ru.learn.javapracticehw.security.model;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.learn.javapracticehw.user.entity.User;

import java.util.Collection;
import java.util.List;

public record SecurityUser(User user) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + user.getRole().name());
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
