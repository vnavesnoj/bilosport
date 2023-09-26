package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "tournamentPerson")
@Builder
@Entity
@Table(name = "tournament_result")
public class TournamentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private TournamentPerson tournamentPerson;

    private Integer place;

    private Integer outOf;
}
