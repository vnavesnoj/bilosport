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
@Table(name = "coach_athlete")
public class CoachAthlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private User coach;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "athlete_id")
    private User athlete;
}
