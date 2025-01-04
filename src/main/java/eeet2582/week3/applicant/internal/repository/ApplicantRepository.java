package eeet2582.week3.applicant.internal.repository;

import eeet2582.week3.applicant.internal.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ApplicantRepository
 */
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

//    Optional<Applicant> findById(String id);
    
} 
