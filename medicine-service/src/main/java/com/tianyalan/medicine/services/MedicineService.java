package com.tianyalan.medicine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.tianyalan.medicine.model.Medicine;
import com.tianyalan.medicine.repository.MedicineRepository;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;	
	
	@Autowired
    private Tracer tracer;
	
	public Medicine getMedicineByCode(String medicineCode) {
		
		Span newSpan = tracer.createSpan("getMedicineByCode");
		
		try {			
			return medicineRepository.findByMedicineCode(medicineCode);   
        }
        finally{
          newSpan.tag("peer.service", "database");
          newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
          tracer.close(newSpan);
        }
	}
}
