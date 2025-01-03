package eeet2582.week3.culture_class;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface CulturalClassRepository extends JpaRepository<CulturalClassEntity, Long> {

//    Optional<CulturalClassEntity> findById(Long id);
    
} 
