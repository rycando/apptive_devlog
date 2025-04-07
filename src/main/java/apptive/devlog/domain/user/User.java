package apptive.devlog.domain.user;

import apptive.devlog.domain.user.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @NotNull
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    @Column(unique = true, nullable = false)
    private String nickname;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserRole",
            joinColumns = { @JoinColumn(name = "userId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")}
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // UserDetails에서 userName은 ID를 의미함
    @Override
    public String getUsername() {
        return this.email;
    }
}
