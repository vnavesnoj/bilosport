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
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tournament_person")
public class TournamentPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tournament tournament;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person person;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TournamentPersonStatus status;

    @OneToOne(mappedBy = "tournamentPerson", fetch = FetchType.LAZY)
    private TournamentResult tournamentResult;

//    public void setPerson(Person person) {
//        this.person = person;
//        this.person.getTournamentPeople().add(this);
//    }
//
//    public void setTournament(Tournament tournament) {
//        this.tournament = tournament;
//        this.tournament.getTournamentPeople().add(this);
//    }
}
