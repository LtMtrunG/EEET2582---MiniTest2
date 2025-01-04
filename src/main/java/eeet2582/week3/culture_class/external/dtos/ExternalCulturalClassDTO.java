package eeet2582.week3.culture_class.external.dtos;

import eeet2582.week3.culture_class.internal.entity.CulturalClassEntity;
import lombok.Setter;

@Setter
public class ExternalCulturalClassDTO {
    private final Long id;
    private final String name;

    public ExternalCulturalClassDTO(CulturalClassEntity culturalClass) {
        this.id = culturalClass.getId();
        this.name = culturalClass.getName();
    }
}
