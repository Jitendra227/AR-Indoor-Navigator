package com.example.indoornavigator.Model;

public class RoomDataModel {
    String id;
    String roomName;
    String floorName;

    public RoomDataModel(String id, String roomName, String floorName) {
        this.id = id;
        this.roomName = roomName;
        this.floorName = floorName;
    }

    public String getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getFloorName() {
        return floorName;
    }
}
