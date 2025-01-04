package eeet2582.week3.applicant.internal.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateApplicantDTO {
    private String name;
    private int age;
    private List<String> culturalClasses;
}
