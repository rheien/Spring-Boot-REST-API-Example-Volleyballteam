package com.example.rle.model;

public enum Position {
    RH("Opposite Hitter"),
    OH("Outside Hitter"),
    MB("Middle Blocker"),
    L("Libero"),
    S("Setter");

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
