package project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.user.model.ERole;
import project.user.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}