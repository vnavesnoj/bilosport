package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(exclude = "person")
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Person person;

    @Column(nullable = false)
    private String password;

    private String firstname;

    private String lastname;

    private String surname;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String image;

    private boolean enabled;
}
