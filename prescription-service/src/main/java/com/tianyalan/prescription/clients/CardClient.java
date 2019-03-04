package com.tianyalan.prescription.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tianyalan.prescription.model.Card;
import com.tianyalan.prescription.repository.CardRedisRepository;

@Component
public class CardClient {

    private static final Logger logger = LoggerFactory.getLogger(CardClient.class);
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    CardRedisRepository cardRedisRepository;
    
    private Card getCardFromCache(Long cardId) {
        try {
            return cardRedisRepository.findCardById(cardId);
        }
        catch (Exception ex){
            return null;
        }
    }

    private void putCardIntoCache(Card card) {
        try {
        	cardRedisRepository.saveCard(card);
        }catch (Exception ex){
        }
    }
   
    public Card getCard(Long cardId){
    	
    	logger.debug("Get card: {}", cardId);
    	
        Card card = getCardFromCache(cardId);
        if (card != null){
            return card;
        }

        ResponseEntity<Card> restExchange =
                restTemplate.exchange(
                        "http://localhost:5555/api/card/v1/cards/{cardId}",
                        HttpMethod.GET,
                        null, Card.class, cardId);
                
        card = restExchange.getBody();
        
        if (card != null) {
        	putCardIntoCache(card);
        }
        
        return card;
    }
}
