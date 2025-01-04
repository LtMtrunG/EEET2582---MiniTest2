package eeet2582.week3.culture_class.internal.repository;

import eeet2582.week3.culture_class.internal.entity.CulturalClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CulturalClassRepository extends JpaRepository<CulturalClassEntity, Long> {

//    Optional<CulturalClassEntity> findById(Long id);
    
} 
