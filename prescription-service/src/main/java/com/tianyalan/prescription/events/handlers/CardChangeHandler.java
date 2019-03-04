package com.tianyalan.prescription.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.tianyalan.prescription.events.CardChangeChannel;
import com.tianyalan.prescription.events.models.CardChangeModel;
import com.tianyalan.prescription.repository.CardRedisRepository;

@EnableBinding(CardChangeChannel.class)
public class CardChangeHandler {

    @Autowired
    private CardRedisRepository cardRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(CardChangeHandler.class);

    @StreamListener("inboundCardChanges")
    public void cardChangeSink(CardChangeModel cardChange) {
    	
        logger.debug("Received a message of type " + cardChange.getType()); 
    	logger.debug("Received a {} event from the card service for card id {}", 
    			cardChange.getOperation(), 
    			cardChange.getCard().getId());
        
        if(cardChange.getOperation().equals("SAVE")) {
            cardRedisRepository.saveCard(cardChange.getCard());
        } else if(cardChange.getOperation().equals("UPDATE")) {
        	cardRedisRepository.updateCard(cardChange.getCard());        	
        } else if(cardChange.getOperation().equals("DELETE")) {
        	cardRedisRepository.deleteCard(cardChange.getCard().getId());
        } else {            
            logger.error("Received an UNKNOWN event from the card service of type {}", cardChange.getType());
        }
    }
}
