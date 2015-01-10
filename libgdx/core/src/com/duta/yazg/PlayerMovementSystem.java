package com.duta.yazg;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerMovementSystem extends EntitySystem {
    private final Entity player;

    public PlayerMovementSystem(Entity player) {
        this.player = player;
    }

    @Override
    public void update(float deltaTime) {
        // Grab a reference to the player sprite
        Sprite ps = Mappers.sprite.get(player).sprite;

        // Handle player movement input
        float pspeed = Mappers.speed.get(player).speed * deltaTime;
        int pxd = 0;
        int pyd = 0;
        if(Gdx.input.isKeyPressed(Keys.UP))    pyd++;
        if(Gdx.input.isKeyPressed(Keys.DOWN))  pyd--;
        if(Gdx.input.isKeyPressed(Keys.LEFT))  pxd--;
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) pxd++;
        if(pxd > 0) ps.setRotation(270f);
        if(pxd < 0) ps.setRotation( 90f);
        if(pyd < 0) ps.setRotation(180f + pxd*45f);
        if(pyd > 0) ps.setRotation(  0f - pxd*45f);
        ps.translate(pxd * pspeed, pyd * pspeed);
    }
}
