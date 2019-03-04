package com.tianyalan.prescription.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.tianyalan.prescription.model.Card;

import javax.annotation.PostConstruct;

@Repository
public class CardRedisRepositoryImpl implements CardRedisRepository {
    private static final String HASH_NAME ="card";

    private RedisTemplate<String, Card> redisTemplate;
    private HashOperations<String, Long, Card> hashOperations;

    public CardRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private CardRedisRepositoryImpl(RedisTemplate<String, Card> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveCard(Card card) {
        hashOperations.put(HASH_NAME, card.getId(), card);
    }

    @Override
    public void updateCard(Card card) {
        hashOperations.put(HASH_NAME, card.getId(), card);
    }

    @Override
    public void deleteCard(Long cardId) {
        hashOperations.delete(HASH_NAME, cardId);
    }

    @Override
    public Card findCardById(Long cardId) {
       return (Card) hashOperations.get(HASH_NAME, cardId);
    }
}
