package com.tianyalan.card.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.card.events.source.CardChangeSource;
import com.tianyalan.card.model.Card;
import com.tianyalan.card.repository.CardRepository;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardChangeSource cardChangeSource;

    public Card getCardById(Long cardId) {
        return cardRepository.findOne(cardId);
    }

    public void saveCard(Card card){
        cardRepository.save(card);
    }

    public void updateCard(Card card){
    	cardRepository.save(card);
    	
    	cardChangeSource.publishCardUpdatedEvent(card);
    }

    public void deleteCard(Card card){
    	cardRepository.delete(card);
    	
    	cardChangeSource.publishCardDeletedEvent(card);
    }
}
