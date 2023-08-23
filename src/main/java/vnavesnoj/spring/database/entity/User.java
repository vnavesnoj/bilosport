package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username", callSuper = false)
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String surname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String image;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserSport> userSports = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<TournamentUser> tournamentUsers = new ArrayList<>();
}
