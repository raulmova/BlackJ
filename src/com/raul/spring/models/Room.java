package com.raul.spring.models;

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
}
