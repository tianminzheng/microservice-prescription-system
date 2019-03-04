package com.tianyalan.prescription.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tianyalan.prescription.model.Medicine;

@Component
public class MedicineClient {
	
    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MedicineClient.class);

    public Medicine getMedicine(String medicineCode){

    	logger.debug("Get medicine: {}", medicineCode);

        ResponseEntity<Medicine> restExchange =
                restTemplate.exchange(
                        "http://localhost:5555/api/medicine/v1/medicines/{medicineCode}", 
                        HttpMethod.GET,
                        null, Medicine.class, medicineCode);
         
        Medicine medicine = restExchange.getBody();

        return medicine;
    }
}