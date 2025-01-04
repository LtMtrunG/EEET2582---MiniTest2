package eeet2582.week3.culture_class.internal.service;

import java.util.List;
import java.util.Optional;

import eeet2582.week3.culture_class.internal.InternalCulturalClassInterface;
import eeet2582.week3.culture_class.internal.dtos.InternalCulturalClassDTO;
import eeet2582.week3.culture_class.internal.entity.CulturalClassEntity;
import eeet2582.week3.culture_class.internal.repository.CulturalClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CulturalClassService implements InternalCulturalClassInterface {

    @Autowired
    private CulturalClassRepository culturalClassRepo;

    public InternalCulturalClassDTO createCulturalClass(CulturalClassEntity routeEntity) {
        CulturalClassEntity culturalClassEntity = culturalClassRepo.save(routeEntity);
        return new InternalCulturalClassDTO(culturalClassEntity);
    }

    public List<InternalCulturalClassDTO> getAllCulturalClasss() {
        System.out.println(culturalClassRepo.findAll());
        return culturalClassRepo.findAll().stream().map(InternalCulturalClassDTO::new).toList();
    }

    public Optional<InternalCulturalClassDTO> updateCulturalClass(CulturalClassEntity routeData) {
        CulturalClassEntity existingCulturalClass = 
            culturalClassRepo.findById(routeData.getId()).get();

        if (existingCulturalClass != null) {
            return Optional.of(new InternalCulturalClassDTO(culturalClassRepo.save(routeData)));
        }
           
        return Optional.empty();
        
    }

    public Optional<InternalCulturalClassDTO> deleteCulturalClass(Long id) {
        CulturalClassEntity existingCulturalClass = culturalClassRepo.findById(id).get();

        if (existingCulturalClass != null) {
            culturalClassRepo.delete(existingCulturalClass);
            return Optional.of(new InternalCulturalClassDTO(existingCulturalClass));
        }

        return Optional.empty();
    }

}
