package com.duta.yazg;

import com.badlogic.gdx.Input.Keys;

public final class ControlSettings {
    public int moveUp, moveDown, moveLeft, moveRight;
    public int shoot;

    public ControlSettings() {
        moveUp    = Keys.W;
        moveDown  = Keys.S;
        moveLeft  = Keys.A;
        moveRight = Keys.D;
        shoot     = Keys.SPACE;
    }
}
