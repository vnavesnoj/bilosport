package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@NamedEntityGraph(
        name = "Tournament.sport",
        attributeNodes = @NamedAttributeNode("sport")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sport sport;

    private LocalDate tournamentDate;

    private Integer minAge;

    private Integer maxAge;

    private Integer participantCount;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Scope scope;

//    @Builder.Default
//    @OneToMany(mappedBy = "tournament")
//    private Set<TournamentPerson> tournamentPeople = new HashSet<>();
}
