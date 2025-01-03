package eeet2582.week3.culture_class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
class CulturalClassGenerator {

    private final CulturalClassRepository culturalClassRepo;

    @Autowired
    public CulturalClassGenerator(CulturalClassRepository culturalClassRepo) {
        this.culturalClassRepo = culturalClassRepo;
    }

    @PostConstruct
    public void generateCulturalClasss() {
        if (culturalClassRepo.findAll().isEmpty()) {
            culturalClassRepo.save(
                new CulturalClassEntity("Piano"
                )
            );

            culturalClassRepo.save(
                new CulturalClassEntity("Wing Chun")
            );
        }
    }
}

