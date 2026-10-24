
package com.bloodbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodbank.entity.Donor;

public interface DonorRepository
        extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroup(String bloodGroup);

    List<Donor> findByCityContainingIgnoreCase(String city);

    List<Donor> findByNameContainingIgnoreCase(String name);
}
