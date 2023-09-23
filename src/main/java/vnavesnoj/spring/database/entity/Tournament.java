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

    @Builder.Default
    @OneToMany(mappedBy = "tournament")
    private Set<TournamentPerson> tournamentPeople = new HashSet<>();
}
