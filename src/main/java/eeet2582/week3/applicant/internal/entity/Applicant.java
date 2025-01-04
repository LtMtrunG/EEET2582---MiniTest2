package eeet2582.week3.applicant.internal.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Applicants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="cultural_classes")
    private List<String> culturalClasses;

//    @Column(name="password")
//    private int password;

    public Applicant(String name, int age) {
        this.name = name;
        this.age = age;
        this.culturalClasses = new ArrayList<>();
    }

    public Applicant(String name, int age, List<String> culturalClasses) {
        this.name = name;
        this.age = age;
        this.culturalClasses = List.copyOf(culturalClasses);
    }
}
