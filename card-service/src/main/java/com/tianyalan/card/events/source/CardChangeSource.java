package com.tianyalan.card.events.source;

import com.tianyalan.card.event.models.CardChangedEvent;
import com.tianyalan.card.model.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CardChangeSource {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(CardChangeSource.class);
  
    @Autowired
    public CardChangeSource(Source source){
        this.source = source;
    }

    private void publishCardChange(String operation, Card card){
    	logger.debug("Sending message for Card Id: {}", card.getId());
    	
        CardChangedEvent event =  new CardChangedEvent(
        		CardChangedEvent.class.getTypeName(),
        		operation,
                card);

        source.output().send(MessageBuilder.withPayload(event).build());
    }
    
    public void publishCardUpdatedEvent(Card card) {
    	publishCardChange("UPDATE", card);
    }
    
    public void publishCardDeletedEvent(Card card) {
    	publishCardChange("DELETE", card);
    }
}
