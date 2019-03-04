package com.tianyalan.prescription.repository;

import com.tianyalan.prescription.model.Card;

public interface CardRedisRepository {
    void saveCard(Card card);
    
    void updateCard(Card card);
    
    void deleteCard(Long cardId);
    
    Card findCardById(Long cardId);
}
