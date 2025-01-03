package eeet2582.week3.applicant.controller;

import java.util.List;
import java.util.Optional;

import eeet2582.week3.applicant.entity.Applicant;
import eeet2582.week3.applicant.internal.InternalApplicantInterface;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;
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
@RequestMapping("/applicant")
@CrossOrigin(origins = "http://localhost:3000")
class ApplicantController {

  @Autowired
  private InternalApplicantInterface internalApplicantInterface;

  @GetMapping
  ResponseEntity<List<InternalApplicantDTO>> getAllApplicants() {
    Optional<List<InternalApplicantDTO>> customers = internalApplicantInterface.getAllApplicants();

    if (!customers.isPresent()) {
      return new ResponseEntity<List<InternalApplicantDTO>>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<List<InternalApplicantDTO>>(
            customers.get(), HttpStatus.OK
    );

  }

  @PostMapping("")
  InternalApplicantDTO createApplicant(@RequestBody CreateApplicantDTO customerData) {
    return internalApplicantInterface.createApplicant(customerData);
  }


  @PutMapping("")
  ResponseEntity<InternalApplicantDTO> updateApplicant(@RequestBody Applicant customerData) {
    Optional<InternalApplicantDTO> updatedApplicant =
      internalApplicantInterface.updateApplicant(customerData);

    if (!updatedApplicant.isPresent()) {
      return new ResponseEntity<InternalApplicantDTO>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<InternalApplicantDTO>(updatedApplicant.get(), HttpStatus.OK);
    }
  }

  @DeleteMapping("/{id}")
  ResponseEntity<InternalApplicantDTO> deleteApplicant(@PathVariable Long id) {
    Optional<InternalApplicantDTO> customer = internalApplicantInterface.deleteApplicant(id);

    if (!customer.isPresent()) {
      return new ResponseEntity<InternalApplicantDTO>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<InternalApplicantDTO>(customer.get(), HttpStatus.OK);
    }
  }

}

