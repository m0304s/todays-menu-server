package idblab.todaysmenu.service;


import idblab.todaysmenu.domain.dto.SignupRequestDto;
import idblab.todaysmenu.domain.entity.User;
import idblab.todaysmenu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(SignupRequestDto signupRequest) {
        if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
        User user = User.builder()
                .userId(UUID.randomUUID())
                .username(signupRequest.getUsername())
                .account(signupRequest.getAccount())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .email(signupRequest.getEmail())
                .gender(User.Gender.valueOf(signupRequest.getGender().name()))
                .birth(signupRequest.getBirth().atStartOfDay())
                .weight(signupRequest.getWeight())
                .height(signupRequest.getHeight())
                .totalKcal(signupRequest.getDaysWalkedPerWeek()*0.1)
                .build();

        return userRepository.save(user);
    }
}