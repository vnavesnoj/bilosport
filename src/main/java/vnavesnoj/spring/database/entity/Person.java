package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@NamedEntityGraph(name = "Person.user.sports",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value = "sport")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
//@ToString(exclude = {
//        "tournamentPeople",
//        "sport"
//})
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

    @ManyToMany
    @JoinTable(name = "person_sport")
    private Set<Sport> sport;

//    @Builder.Default
//    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
//    private Set<TournamentPerson> tournamentPeople = new HashSet<>();

}
