package eeet2582.week3.culture_class.internal;

import eeet2582.week3.culture_class.internal.dtos.InternalCulturalClassDTO;
import eeet2582.week3.culture_class.internal.entity.CulturalClassEntity;

import java.util.List;
import java.util.Optional;

public interface InternalCulturalClassInterface {
    public InternalCulturalClassDTO createCulturalClass(CulturalClassEntity routeEntity);
    public List<InternalCulturalClassDTO> getAllCulturalClasss();
    public Optional<InternalCulturalClassDTO> updateCulturalClass(CulturalClassEntity routeData);
    public Optional<InternalCulturalClassDTO> deleteCulturalClass(Long id);
}
