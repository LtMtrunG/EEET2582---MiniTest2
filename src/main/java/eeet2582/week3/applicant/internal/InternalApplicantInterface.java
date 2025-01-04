package eeet2582.week3.applicant.internal;

import eeet2582.week3.applicant.entity.Applicant;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface InternalApplicantInterface {
    public Page<InternalApplicantDTO> getAllApplicants(String keyword, int pageNo, int pageSize);
    public InternalApplicantDTO createApplicant(CreateApplicantDTO applicant);
    public Optional<InternalApplicantDTO> updateApplicant(InternalApplicantDTO customerData);
    public Optional<InternalApplicantDTO> deleteApplicant(Long id);
}
