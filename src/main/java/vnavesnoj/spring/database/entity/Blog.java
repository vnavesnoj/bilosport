package vnavesnoj.spring.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    private String announcement;

    private LocalDateTime publication_time;

    @OneToOne(fetch = FetchType.LAZY)
    private BlogBody blogBody;
}
