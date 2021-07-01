package project.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.grade.model.ESubject;
import project.grade.model.Subject;
import project.user.model.ERole;
import project.user.model.Role;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(ESubject name);
}
