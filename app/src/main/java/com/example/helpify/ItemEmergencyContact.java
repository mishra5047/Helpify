package com.example.helpify;

public class ItemEmergencyContact {
    String name, imageUrl, phoneNumber, relation;

    public ItemEmergencyContact() {
    }

    public ItemEmergencyContact(String name, String imageUrl, String phoneNumber, String relation) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.relation = relation;
    }
}
