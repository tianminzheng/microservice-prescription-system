package com.tianyalan.card.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tianyalan.card.model.Card;
import com.tianyalan.card.services.CardService;

@RestController
@RequestMapping(value = "v1/cards")
public class CardController {
	@Autowired
	private CardService cardService;

	private static final Logger logger = LoggerFactory.getLogger(CardController.class);

	@RequestMapping(value = "/{cardId}", method = RequestMethod.GET)
	public Card getCard(@PathVariable("cardId") Long cardId) {
		
		logger.info("Get card by id: {} ", cardId);
		
		 Card card = cardService.getCardById(cardId);
		 return card;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void updateCard(@RequestBody Card card) {
		cardService.updateCard(card);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void saveCard(@RequestBody Card card) {
		cardService.updateCard(card);
	}

	@RequestMapping(value = "/{cardId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCard(@PathVariable("cardId") Long cardId) {
		Card card = new Card();
		card.setId(cardId);

		cardService.deleteCard(card);
	}
}
