package com.tianyalan.prescription.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tianyalan.prescription.model.Prescription;
import com.tianyalan.prescription.services.PrescriptionService;

@RestController
@RequestMapping(value="v1/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
	
	@RequestMapping(value = "/{cardId}/{medicineCode}", method = RequestMethod.POST)
	public Prescription savePrescription( @PathVariable("accountId") Long cardId,
            @PathVariable("medicineCode") String medicineCode) {
		Prescription prescription = prescriptionService.addPrescription(cardId, medicineCode);		
		
		return prescription;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Prescription getPrescription(@PathVariable Long id) {
		Prescription prescription = prescriptionService.getPrescriptionById(id);
		
    	return prescription;
    }
	
	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public List<Prescription> getPrescriptionList( @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		List<Prescription> prescriptions = prescriptionService.getPrescriptions(pageIndex, pageSize);
		
		return prescriptions;
	}	
}
