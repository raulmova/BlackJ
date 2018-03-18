package com.raul.spring.models;

import com.raul.spring.GameManager;

import java.util.Vector;

public class Room {
    private String roomID;
    private int numberOfPlayers;
    private String state;
    private int i_turn;
    private Vector<UserCard> userCards;
    private Vector<Integer> availableCards;

    public Room(String roomID, int numberOfPlayers) {
        this.roomID = roomID;
        this.numberOfPlayers = numberOfPlayers;
    }

    public Room(String roomID) {
        this.roomID = roomID;
        this.numberOfPlayers = 0;
        this.state = "active";
        this.i_turn = 0;
        this.userCards = new Vector<>();
        this.availableCards = GameManager.generateDeck();
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getI_turn() {
        return i_turn;
    }

    public void setI_turn(int i_turn) {
        this.i_turn = i_turn;
    }

    public Vector<UserCard> getUserCards() {
        return userCards;
    }

    public void setUserCards(Vector<UserCard> userCards) {
        this.userCards = userCards;
    }

    public Vector<Integer> getAvailableCards() {
        return availableCards;
    }

    public void setAvailableCards(Vector<Integer> availableCards) {
        this.availableCards = availableCards;
    }

    public void nextTurn(){
        UserCard current = this.userCards.get(i_turn);
        current.setStatus(UserCard.WAITING);
        this.i_turn = i_turn+1;
        this.i_turn = i_turn% numberOfPlayers;
        UserCard next = this.userCards.get(i_turn);
        next.setStatus(UserCard.ON_TURN);
    }

    public void addNewPlayer(String playerID){
        UserCard tempUserCard = new UserCard(playerID,new Vector()); // VENIR AQUI SI CAGAMOS
        if (numberOfPlayers == 0){
            tempUserCard.setStatus(UserCard.ON_TURN);
        }
        this.numberOfPlayers ++;
        this.userCards.add(tempUserCard);
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomID='" + roomID + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }

    public String stringToJSON(){
        return"{" +
                "\"roomID\":\"" + roomID +"\"," +
                "\"numberOfPlayers\":\"" + numberOfPlayers +
                "\"}";
    }

    public String getGameStatsJSON(){
        return "{" +
                "\"state\":\"" + state +"\"," +
                "\"i_turn\":" + i_turn + "," +
                "\"userCards\":" + "[" +getUserCardsJSON() + "]}";

    }

    public String getUserCardsJSON(){
        String json ="";

        for (int i=0; i < userCards.size(); i++){
            json += "{ \"userCards\" :" + userCards.get(i).toJSON() + "}";
            if (i!=userCards.size()-1){
                json+=",";
            }
        }

        return json;
    }
}
