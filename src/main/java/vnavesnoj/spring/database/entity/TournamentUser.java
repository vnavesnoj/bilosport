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
@Table(name = "tournament_users")
public class TournamentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tournament tournament;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    public void setUser(User user) {
        this.user = user;
        this.user.getTournamentUsers().add(this);
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
        this.tournament.getTournamentUsers().add(this);
    }
}
