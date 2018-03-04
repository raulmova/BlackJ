package com.raul.spring.models;

import java.util.Vector;

public class UserCard {
    private String playerID;
    private Vector<Integer> cards;
    private String status;

    public UserCard(String playerID, Vector cards, String status) {
        this.playerID = playerID;
        this.cards = cards;
        this.status = status;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public Vector getCards() {
        return cards;
    }

    public void setCards(Vector cards) {
        this.cards = cards;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
