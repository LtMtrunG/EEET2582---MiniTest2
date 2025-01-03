package eeet2582.week3.applicant.internal.dtos;

import eeet2582.week3.applicant.entity.Applicant;
import lombok.Getter;

import java.util.List;

@Getter
public class InternalApplicantDTO {
    private final String name;
    private final int age;
    private final List<String> culturalClasses;

    public InternalApplicantDTO(Applicant applicant) {
        this.name = applicant.getName();
        this.age = applicant.getAge();
        this.culturalClasses = applicant.getCulturalClasses();
    }

}
