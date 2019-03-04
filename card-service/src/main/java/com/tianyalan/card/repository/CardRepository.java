package com.tianyalan.card.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tianyalan.card.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long>  {

}
