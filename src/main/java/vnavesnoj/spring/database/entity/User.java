package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(exclude = {"userSports", "tournamentUsers"})
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstname;

    private String lastname;

    private String surname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String image;

    private boolean enabled;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private Set<UserSport> userSports = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private Set<TournamentUser> tournamentUsers = new HashSet<>();
}
