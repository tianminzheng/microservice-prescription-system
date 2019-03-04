package com.tianyalan.medicine.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tianyalan.medicine.model.Medicine;
import com.tianyalan.medicine.services.MedicineService;

@RestController
@RequestMapping(value="v1/medicines")
public class MedicineController {

    private static final Logger logger = LoggerFactory.getLogger(MedicineController.class);
    
	@Autowired
	MedicineService medicineService;
	
    @Autowired
    private HttpServletRequest request;
    	
	@RequestMapping(value = "/{medicineCode}", method = RequestMethod.GET)
    public Medicine getMedicine(@PathVariable String medicineCode) {		

		logger.info("Get medicine by code: {} from port: {}", medicineCode, request.getServerPort());
		
		Medicine medicine = medicineService.getMedicineByCode(medicineCode);
    	return medicine;
    }
}
