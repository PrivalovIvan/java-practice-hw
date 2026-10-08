package ru.learn.javapracticehw.security.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.learn.javapracticehw.security.model.SecurityUser;
import ru.learn.javapracticehw.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким email не найден"));
    }
}
