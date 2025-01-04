package eeet2582.week3.culture_class.internal.dtos;

import eeet2582.week3.culture_class.internal.entity.CulturalClassEntity;
import jakarta.persistence.Column;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class InternalCulturalClassDTO implements Serializable {
    private final Long id;
    private final String name;

    public InternalCulturalClassDTO(CulturalClassEntity culturalClass) {
        this.id = culturalClass.getId();
        this.name = culturalClass.getName();
    }
}
