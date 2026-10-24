
package com.bloodbank.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bloodbank.entity.Donor;
import com.bloodbank.service.DonorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "http://localhost:8080")
public class DonorController {

    private final DonorService service;

    public DonorController(DonorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Donor> addDonor(
            @Valid @RequestBody Donor donor) {

        Donor saved = service.addDonor(donor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return service.getAllDonors();
    }

    @GetMapping("/{id}")
    public Donor getDonor(@PathVariable Long id) {
        return service.getDonorById(id);
    }

    @GetMapping("/search")
    public List<Donor> searchByBloodGroup(
            @RequestParam String bloodGroup) {

        return service.searchByBloodGroup(bloodGroup);
    }

    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable Long id,
            @Valid @RequestBody Donor donor) {

        return service.updateDonor(id, donor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(
            @PathVariable Long id) {

        service.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }
}
