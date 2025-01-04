package eeet2582.week3.applicant.controller;

import java.util.List;
import java.util.Optional;

import eeet2582.week3.applicant.entity.Applicant;
import eeet2582.week3.applicant.internal.InternalApplicantInterface;
import eeet2582.week3.applicant.internal.dtos.CreateApplicantDTO;
import eeet2582.week3.applicant.internal.dtos.InternalApplicantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicant")
@CrossOrigin(origins = "http://localhost:3000")
class ApplicantController {

  @Autowired
  private InternalApplicantInterface internalApplicantInterface;

  @GetMapping
  ResponseEntity<Page<InternalApplicantDTO>> getAllApplicants(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
    Page<InternalApplicantDTO> customers = internalApplicantInterface.getAllApplicants(keyword, pageNo, pageSize);

    if (!customers.hasContent()) {
      return new ResponseEntity<Page<InternalApplicantDTO>>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @PostMapping("")
  InternalApplicantDTO createApplicant(@RequestBody CreateApplicantDTO customerData) {
    return internalApplicantInterface.createApplicant(customerData);
  }


  @PutMapping("")
  ResponseEntity<InternalApplicantDTO> updateApplicant(@RequestBody InternalApplicantDTO customerData) {
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

