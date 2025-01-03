package eeet2582.week3.applicant;

import eeet2582.week3.applicant.entity.Applicant;
import eeet2582.week3.applicant.internal.InternalApplicantInterface;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicantGenerator {

    private final ApplicantRepository applicantRepo;

    private final InternalApplicantInterface internalApplicantInterface;

    @PostConstruct
    public void generateApplicants() {
        if (applicantRepo.findAll().isEmpty()) {
            List<CreateApplicantDTO> customers = List.of(
                new CreateApplicantDTO("Thao Nguyen", 10, new ArrayList<>()),
                new CreateApplicantDTO("Thu Huynh", 14, new ArrayList<>()),
                new CreateApplicantDTO("Trong Le", 20, new ArrayList<>()),
                new CreateApplicantDTO("John Thompson", 22, new ArrayList<>()),
                new CreateApplicantDTO("Yoon Lee", 18, new ArrayList<>())
            );
            
            for (CreateApplicantDTO applicant : customers) {
                internalApplicantInterface.createApplicant(applicant);
            }
            // applicantRepo.saveAll(customers);
        }
        
    }
}

