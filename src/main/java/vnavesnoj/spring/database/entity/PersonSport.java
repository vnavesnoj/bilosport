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
@Table(name = "person_sport")
public class PersonSport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person person;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sport sport;

    public void setPerson(Person person) {
        this.person = person;
        this.person.getPersonSports().add(this);
    }

    public void setSport(Sport sport) {
        this.sport = sport;
        this.sport.getPersonSports().add(this);
    }
}
