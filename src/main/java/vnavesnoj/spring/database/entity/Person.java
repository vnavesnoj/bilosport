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
@ToString(exclude = {
        "personSports",
        "tournamentPeople"
})
@Builder
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private String surname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<PersonSport> personSports = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<TournamentPerson> tournamentPeople = new HashSet<>();

}
