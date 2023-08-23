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
@Table(name = "users_sport")
public class UserSport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sport sport;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserSports().add(this);
    }

    public void setChat(Sport sport) {
        this.sport = sport;
        this.sport.getUserSports().add(this);
    }
}
