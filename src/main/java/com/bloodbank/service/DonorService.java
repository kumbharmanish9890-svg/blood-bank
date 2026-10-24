
package com.bloodbank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodbank.entity.Donor;
import com.bloodbank.repository.DonorRepository;

@Service
public class DonorService {

    private final DonorRepository repository;

    public DonorService(DonorRepository repository) {
        this.repository = repository;
    }

    public Donor addDonor(Donor donor) {
        if (donor.getRegistrationDate() == null) {
            throw new IllegalArgumentException(
                "Registration date is required"
            );
        }

        return repository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return repository.findAll();
    }

    public Donor getDonorById(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Donor not found")
            );
    }

    public List<Donor> searchByBloodGroup(String group) {
        return repository.findByBloodGroup(group);
    }

    public Donor updateDonor(Long id, Donor updatedDonor) {
        Donor existing = getDonorById(id);

        existing.setName(updatedDonor.getName());
        existing.setBloodGroup(updatedDonor.getBloodGroup());
        existing.setPhone(updatedDonor.getPhone());
        existing.setEmail(updatedDonor.getEmail());
        existing.setCity(updatedDonor.getCity());

        return repository.save(existing);
    }

    public void deleteDonor(Long id) {
        Donor donor = getDonorById(id);
        repository.delete(donor);
    }
}
