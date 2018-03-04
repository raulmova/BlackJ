package com.raul.spring.models;

public class User {
    private String userID;
    private String token;
    private String roomID;

    public User(String userID, String token, String roomID) {
        this.userID = userID;
        this.token = token;
        this.roomID = roomID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", token='" + token + '\'' +
                ", roomID='" + roomID + '\'' +
                '}';
    }

}
