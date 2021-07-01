package project;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.grade.dto.GradeDTO;
import project.grade.service.GradeService;
import project.security.dto.SignupRequest;
import project.security.service.AuthService;
import project.student.dto.StudentDTO;
import project.student.mapper.StudentMapper;
import project.student.model.Student;
import project.student.repository.StudentRepository;
import project.student.service.StudentService;
import project.user.model.ERole;
import project.user.model.Role;
import project.user.model.User;
import project.user.repository.RoleRepository;
import project.user.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {
    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final GradeService gradeService;

    private final StudentService studentService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            User user = User.builder()
                    .name("Administrator")
                    .email("admin@email.com")
                    .username("admin")
                    .password(passwordEncoder.encode("admin1!"))
                    .build();
            Role role = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Cannot find ADMIN role"));
            user.setRole(role);
            userRepository.save(user);


            User teacher1 = User.builder()
                    .name("Teacher 1")
                    .email("teacher1@email.com")
                    .username("teacher1")
                    .password(passwordEncoder.encode("teacher1!"))
                    .build();
            Role role1 = roleRepository.findByName(ERole.TEACHER)
                    .orElseThrow(() -> new RuntimeException("Cannot find TEACHER role"));
            teacher1.setRole(role1);
            userRepository.save(teacher1);

            User teacher2 = User.builder()
                    .name("Teacher 2")
                    .email("teacher2@email.com")
                    .username("teacher2")
                    .password(passwordEncoder.encode("teacher2!"))
                    .build();
            Role role2 = roleRepository.findByName(ERole.TEACHER)
                    .orElseThrow(() -> new RuntimeException("Cannot find TEACHER role"));
            teacher2.setRole(role2);
            userRepository.save(teacher2);

            studentService.create(StudentDTO.builder()
                    .name("Ioana Surdu")
                    .email("ioanaandreeasurdu@yahoo.com")
                    .address("Otelarilor Street 12")
                    .cnp("2990519124608")
                    .build());
            studentService.create(StudentDTO.builder()
                    .name("Diana Negrea")
                    .email("diananegrea@yahoo.com")
                    .address("Otelarilor Street 2")
                    .cnp("2991207124615")
                    .build());
        }
    }
}
