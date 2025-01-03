package eeet2582.week3.applicant.internal;

import eeet2582.week3.applicant.entity.Applicant;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;

import java.util.List;
import java.util.Optional;

public interface InternalApplicantInterface {
    public Optional<List<InternalApplicantDTO>> getAllApplicants();
    public InternalApplicantDTO createApplicant(CreateApplicantDTO applicant);
    public Optional<InternalApplicantDTO> updateApplicant(Applicant customerData);
    public Optional<InternalApplicantDTO> deleteApplicant(Long id);
}
