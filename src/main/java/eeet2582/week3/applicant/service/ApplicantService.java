package eeet2582.week3.applicant.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import eeet2582.week3.applicant.entity.Applicant;
import eeet2582.week3.applicant.external.ExternalApplicantInterface;
import eeet2582.week3.applicant.internal.InternalApplicantInterface;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;
import eeet2582.week3.applicant.repository.ApplicantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Transactional
class ApplicantService implements InternalApplicantInterface, ExternalApplicantInterface {

    @Autowired
    private ApplicantRepository destRepository;

    public InternalApplicantDTO createApplicant(CreateApplicantDTO applicant) {

        System.out.println(applicant.toString());
        Applicant savedApplicant = new Applicant();
        savedApplicant.setName(applicant.getName());
        savedApplicant.setAge(applicant.getAge());
        savedApplicant.setCulturalClasses(applicant.getCulturalClasses());

        destRepository.save(savedApplicant);

        return new InternalApplicantDTO(savedApplicant);
    }

    public Page<InternalApplicantDTO> getAllApplicants(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());

        Page<Applicant> applicantsPage = destRepository.findAll(pageable);

        List<InternalApplicantDTO> applicantDTOList = applicantsPage.getContent().stream()
                .map(InternalApplicantDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(applicantDTOList, pageable, applicantsPage.getTotalElements());
    }

    public Optional<InternalApplicantDTO> updateApplicant(Applicant customerData) {
        Applicant existingApplicant = 
            destRepository.findById(customerData.getId()).get();
            

        if (existingApplicant != null) {
            return Optional.of(new InternalApplicantDTO(destRepository.save(customerData)));
        }
           
        return Optional.empty();
        
    }

    public Optional<InternalApplicantDTO> deleteApplicant(Long id) {
        Applicant existingApplicant = destRepository.findById(id).get();

        if (existingApplicant != null) {
            destRepository.delete(existingApplicant);
            return Optional.of(new InternalApplicantDTO(existingApplicant));
        }

        return Optional.empty();
    }

}
