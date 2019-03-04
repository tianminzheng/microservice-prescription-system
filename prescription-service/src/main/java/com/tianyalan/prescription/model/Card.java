package com.tianyalan.prescription.model;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String cardCode;    
    private String cardName;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

}