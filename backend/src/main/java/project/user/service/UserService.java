package project.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.user.dto.UserDTO;
import project.user.mapper.UserMapper;
import project.user.model.ERole;
import project.user.model.Role;
import project.user.model.User;
import project.user.repository.RoleRepository;
import project.user.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::fromUser)
                .collect(toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public UserDTO create(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .build();

       Role role;
       if(userDTO.getRole() == null) {
           role = roleRepository.findByName(ERole.TEACHER)
                   .orElseThrow(() -> new RuntimeException("Cannot find TEACHER role"));
       } else {
           role = roleRepository.findByName(ERole.valueOf(userDTO.getRole()))
                   .orElseThrow(() -> new RuntimeException("Cannot find role: " + userDTO.getRole()));
       }
        user.setRole(role);
        return userMapper.fromUser(userRepository.save(user));
    }

    public UserDTO edit(UserDTO userDTO){
        User user = findById(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        Role role;
        if(userDTO.getRole() == null) {
            role = roleRepository.findByName(ERole.TEACHER)
                    .orElseThrow(() -> new RuntimeException("Cannot find TEACHER role"));
        } else {
            role = roleRepository.findByName(ERole.valueOf(userDTO.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + userDTO.getRole()));
        }
        user.setRole(role);
        return userMapper.fromUser(userRepository.save(user));
    }

    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }

}
