package com.tianyalan.medicine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tianyalan.medicine.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	Medicine findByMedicineCode(String medicineCode);
}
