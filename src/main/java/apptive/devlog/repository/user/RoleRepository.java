package apptive.devlog.repository.user;

import apptive.devlog.domain.user.role.Role;
import apptive.devlog.domain.user.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(RoleName name);
}
