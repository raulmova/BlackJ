package com.raul.spring.models;

import java.util.Vector;

public class UserCard {

    public static String ON_TURN = "ON TURN";
    public static String WAITING = "WAITING";

    private String playerID;
    private Vector<Integer> cards;
    private String status;

    public UserCard(String playerID, Vector cards) {
        this.playerID = playerID;
        this.cards = cards;
        this.status = WAITING;
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

    public String toJSON(){
        return "{" +
                "\"playerID\":\"" + playerID +"\"," +
                "\"status\":\"" + status +"\"," +
                "\"cards\":" + "[" + cardsToJSON() +"]}";

    }

    private String cardsToJSON(){
        String json ="";

        for (int i=0; i < cards.size(); i++){
            json += "{ \"cardKey\" :" + cards.get(i) + "}";
            if (i!=cards.size()-1){
                json+=",";
            }
        }

        return json;
    }
}
