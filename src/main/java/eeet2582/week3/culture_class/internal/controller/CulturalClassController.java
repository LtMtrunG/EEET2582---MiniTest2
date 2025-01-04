package eeet2582.week3.culture_class.internal.controller;

import java.util.List;
import java.util.Optional;

import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;
import eeet2582.week3.culture_class.internal.dtos.InternalCulturalClassDTO;
import eeet2582.week3.culture_class.internal.entity.CulturalClassEntity;
import eeet2582.week3.culture_class.internal.service.CulturalClassService;
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
public class CulturalClassController {

  @Autowired
  private CulturalClassService culturalClassService;

  @GetMapping
  ResponseEntity<List<InternalCulturalClassDTO>> getAllCulturalClasss() {
    List<InternalCulturalClassDTO> culturalClassses = culturalClassService.getAllCulturalClasss();

    return ResponseEntity.ok(culturalClassses);
  }

  @PostMapping("")
  InternalCulturalClassDTO createCulturalClass(@RequestBody CulturalClassEntity culturalClassData) {
    return culturalClassService.createCulturalClass(culturalClassData);
  }

  @PutMapping("")
  ResponseEntity<InternalCulturalClassDTO> updateCulturalClass(@RequestBody CulturalClassEntity culturalClassData) {
    Optional<InternalCulturalClassDTO> updatedCulturalClass =
      culturalClassService.updateCulturalClass(culturalClassData);

    if (!updatedCulturalClass.isPresent()) {
      return new ResponseEntity<InternalCulturalClassDTO>(HttpStatus.NOT_FOUND);
    } else {   
      return new ResponseEntity<InternalCulturalClassDTO>(updatedCulturalClass.get(), HttpStatus.OK);
    }
     
  }

  @DeleteMapping("/{id}")
  ResponseEntity<InternalCulturalClassDTO> deleteCulturalClass(@PathVariable Long id) {
    Optional<InternalCulturalClassDTO> culturalClass = culturalClassService.deleteCulturalClass(id);

    if (!culturalClass.isPresent()) {
      return new ResponseEntity<InternalCulturalClassDTO>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<InternalCulturalClassDTO>(culturalClass.get(), HttpStatus.OK);
    }
  }

}

