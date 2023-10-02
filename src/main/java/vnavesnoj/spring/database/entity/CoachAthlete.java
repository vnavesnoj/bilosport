package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@NamedEntityGraph(name = "CoachAthlete.person.user",
        attributeNodes = {
                @NamedAttributeNode(value = "coach",
                        subgraph = "Coach.user"),
                @NamedAttributeNode(value = "athlete",
                        subgraph = "Athlete.user")
        },
        subgraphs = {
                @NamedSubgraph(name = "Coach.user",
                        type = Person.class,
                        attributeNodes = @NamedAttributeNode("user")
                ),
                @NamedSubgraph(name = "Athlete.user",
                        type = Person.class,
                        attributeNodes = @NamedAttributeNode("user")
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "coach_athlete")
public class CoachAthlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Person coach;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete_id")
    private Person athlete;
}
