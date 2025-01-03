package eeet2582.week3.culture_class;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cultural_class")
@CrossOrigin(origins = "http://localhost:3000")
class CulturalClassController {

  @Autowired
  private CulturalClassService culturalClassService;

  @GetMapping
  ResponseEntity<List<CulturalClassEntity>> getAllCulturalClasss() {
    List<CulturalClassEntity> culturalClassses = culturalClassService.getAllCulturalClasss();

    return ResponseEntity.ok(culturalClassses);
  }

  @PostMapping("")
  CulturalClassEntity createCulturalClass(@RequestBody CulturalClassEntity culturalClassData) {
    return culturalClassService.createCulturalClass(culturalClassData);
  }

  @PutMapping("")
  ResponseEntity<CulturalClassEntity> updateCulturalClass(@RequestBody CulturalClassEntity culturalClassData) {
    Optional<CulturalClassEntity> updatedCulturalClass = 
      culturalClassService.updateCulturalClass(culturalClassData);

    if (!updatedCulturalClass.isPresent()) {
      return new ResponseEntity<CulturalClassEntity>(HttpStatus.NOT_FOUND);
    } else {   
      return new ResponseEntity<CulturalClassEntity>(updatedCulturalClass.get(), HttpStatus.OK);
    }
     
  }

  @DeleteMapping("/{id}")
  ResponseEntity<CulturalClassEntity> deleteCulturalClass(@PathVariable Long id) {
    Optional<CulturalClassEntity> culturalClass = culturalClassService.deleteCulturalClass(id);

    if (!culturalClass.isPresent()) {
      return new ResponseEntity<CulturalClassEntity>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<CulturalClassEntity>(culturalClass.get(), HttpStatus.OK);
    }
  }

}

