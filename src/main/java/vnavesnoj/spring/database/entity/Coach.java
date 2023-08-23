package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "user")
@ToString(of = "user")
@Builder
@Entity
public class Coach {

    @Id
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "coach")
    private List<CoachAthlete> coachAthletes = new ArrayList<>();
}
