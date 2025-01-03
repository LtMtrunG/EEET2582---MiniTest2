package eeet2582.week3.applicant.repository;

import eeet2582.week3.applicant.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ApplicantRepository
 */
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

//    Optional<Applicant> findById(String id);
    
} 
