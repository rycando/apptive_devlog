package apptive.devlog.domain.user.role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;
}