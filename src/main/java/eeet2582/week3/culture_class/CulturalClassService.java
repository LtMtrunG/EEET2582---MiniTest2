package eeet2582.week3.culture_class;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Transactional
class CulturalClassService {

    @Autowired
    private CulturalClassRepository culturalClassRepo;


    CulturalClassEntity createCulturalClass(CulturalClassEntity routeEntity) {
        return culturalClassRepo.save(routeEntity);
    }

    public List<CulturalClassEntity> getAllCulturalClasss() {
        System.out.println(culturalClassRepo.findAll());
        return culturalClassRepo.findAll();
    }

    public Optional<CulturalClassEntity> updateCulturalClass(CulturalClassEntity routeData) {
        CulturalClassEntity existingCulturalClass = 
            culturalClassRepo.findById(routeData.getId()).get();

        if (existingCulturalClass != null) {
            return Optional.of(culturalClassRepo.save(routeData));
        }
           
        return Optional.empty();
        
    }

    public Optional<CulturalClassEntity> deleteCulturalClass(Long id) {
        CulturalClassEntity existingCulturalClass = culturalClassRepo.findById(id).get();

        if (existingCulturalClass != null) {
            culturalClassRepo.delete(existingCulturalClass);
            return Optional.of(existingCulturalClass);
        }

        return Optional.empty();
    }

}
