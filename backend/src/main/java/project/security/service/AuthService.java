package project.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.security.dto.SignupRequest;
import project.user.model.ERole;
import project.user.model.Role;
import project.user.model.User;
import project.user.repository.RoleRepository;
import project.user.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(SignupRequest signUpRequest) {
        User user = User.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        String rolesStr = signUpRequest.getRole();
        Role role;

        if (rolesStr == null) {
            role = roleRepository.findByName(ERole.TEACHER)
                    .orElseThrow(() -> new RuntimeException("Cannot find TEACHER role"));
        } else {
            role = roleRepository.findByName(ERole.valueOf(rolesStr))
                        .orElseThrow(() -> new RuntimeException("Cannot find role: " + rolesStr));
        }
        user.setRole(role);
        userRepository.save(user);
    }
}

