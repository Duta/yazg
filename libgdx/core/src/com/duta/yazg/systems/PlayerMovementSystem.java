package com.duta.yazg.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.duta.yazg.ControlSettings;
import com.duta.yazg.Mappers;

public final class PlayerMovementSystem extends EntitySystem {
    private final Entity player;
    private final ControlSettings controls;

    public PlayerMovementSystem(Entity player, ControlSettings controls) {
        this.player = player;
        this.controls = controls;
    }

    @Override
    public void update(float deltaTime) {
        // Grab a reference to the player sprite
        Sprite ps = Mappers.sprite.get(player).sprite;

        // Handle player movement input
        float pspeed = Mappers.speed.get(player).speed * deltaTime;
        int pxd = 0;
        int pyd = 0;
        if(Gdx.input.isKeyPressed(controls.moveUp))    pyd++;
        if(Gdx.input.isKeyPressed(controls.moveDown))  pyd--;
        if(Gdx.input.isKeyPressed(controls.moveLeft))  pxd--;
        if(Gdx.input.isKeyPressed(controls.moveRight)) pxd++;
        if(pxd > 0) ps.setRotation(270f);
        if(pxd < 0) ps.setRotation( 90f);
        if(pyd < 0) ps.setRotation(180f + pxd*45f);
        if(pyd > 0) ps.setRotation(  0f - pxd*45f);
        ps.translate(pxd * pspeed, pyd * pspeed);
    }
}
