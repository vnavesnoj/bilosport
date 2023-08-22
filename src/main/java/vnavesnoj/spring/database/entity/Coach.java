package vnavesnoj.spring.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Coach {

    @Id
    @OneToOne
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "coach")
    private List<CoachAthlete> coachAthletes = new ArrayList<>();
}
