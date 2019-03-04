package com.tianyalan.medicine.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tianyalan.medicine.model.Medicine;

@Service
public class DiscoveryService {
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	public Medicine getProduct(String productCode) {
		List<ServiceInstance> instances = discoveryClient.getInstances("productservice");

		if (instances.size() == 0)
			return null;

		String serviceUri = String.format("%s/v1/products/%s", instances.get(0).getUri().toString(), productCode);

		ResponseEntity<Medicine> result = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Medicine.class,
				productCode);

		return result.getBody();
	}
}
