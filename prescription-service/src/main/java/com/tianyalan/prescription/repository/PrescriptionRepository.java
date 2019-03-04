package com.tianyalan.prescription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tianyalan.prescription.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

}
