package eeet2582.week3.culture_class.internal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cultural_class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CulturalClassEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    public CulturalClassEntity(String name) {
        this.name = name;
    }
}