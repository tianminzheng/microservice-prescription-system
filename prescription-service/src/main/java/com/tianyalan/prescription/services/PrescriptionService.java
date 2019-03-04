package com.tianyalan.prescription.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tianyalan.prescription.clients.CardClient;
import com.tianyalan.prescription.clients.MedicineClient;
import com.tianyalan.prescription.model.Card;
import com.tianyalan.prescription.model.Medicine;
import com.tianyalan.prescription.model.Prescription;
import com.tianyalan.prescription.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private MedicineClient medicineClient;

	@Autowired
	private CardClient cardClient;

	private static final Logger logger = LoggerFactory.getLogger(PrescriptionService.class);

	@HystrixCommand
	private Card getCard(Long cardId) {

		return cardClient.getCard(cardId);
	}

	@HystrixCommand
	private Medicine getMedicine(String medicineCode) {
		
		return medicineClient.getMedicine(medicineCode);
	}

	public Prescription addPrescription(Long cardId, String medicineCode) {		
		logger.debug("addPrescription with card: {} and medicine: {}", cardId, medicineCode);
		
		Prescription prescription = new Prescription();

		Medicine medicine = getMedicine(medicineCode);
		if (medicine == null) {
			return prescription;
		}
		
		logger.debug("get medicine: {} is successful", medicineCode);

		Card card = getCard(cardId);
		if (card == null) {
			return prescription;
		}
		
		logger.debug("get card: {} is successful", cardId);

		prescription.setCardId(cardId);
		prescription.setMedicineId(medicine.getId());
		prescription.setCreateTime(new Date());

		prescriptionRepository.save(prescription);

		return prescription;
	}

	@HystrixCommand(fallbackMethod = "getPrescriptionsFallback")
	public List<Prescription> getPrescriptions(int pageIndex, int pageSize) {

		return prescriptionRepository.findAll(new PageRequest(pageIndex - 1, pageSize)).getContent();
	}

	private List<Prescription> getPrescriptionsFallback(int pageIndex, int pageSize) {
		List<Prescription> fallbackList = new ArrayList<>();

		Prescription order = new Prescription();
		order.setId(0L);
		order.setCardId(0L);
		order.setMedicineId(0L);
		order.setCreateTime(new Date());

		fallbackList.add(order);
		return fallbackList;
	}

	public Prescription getPrescriptionById(Long id) {
		return prescriptionRepository.findOne(id);
	}
}
