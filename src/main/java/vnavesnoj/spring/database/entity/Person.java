package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "person")
    private Set<TournamentPerson> tournamentPeople = new HashSet<>();
}
