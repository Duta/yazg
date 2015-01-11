package com.duta.yazg.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.duta.yazg.Mappers;
import com.duta.yazg.YAZG;
import com.duta.yazg.components.SpeedComponent;
import com.duta.yazg.components.SpriteComponent;

import static com.duta.yazg.Entities.*;

public class PlayerShootingSystem extends EntitySystem {
    private final YAZG game;
    private final Entity player;
    private Engine engine;

    public PlayerShootingSystem(YAZG game, Entity player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void removedFromEngine(Engine engine) {
        this.engine = null;
    }

    @Override
    public void update(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(game.controls.shoot)) {
            Entity bullet = speedy(bullet(sprite()));

            Sprite ps = Mappers.sprite.get(player).sprite;

            SpriteComponent sprite = Mappers.sprite.get(bullet);
            sprite.sprite = new Sprite(game.assets.<Texture>get("bullet.png"));
            sprite.sprite.setSize(5f, 5f);
            sprite.sprite.setOrigin(2.5f, 2.5f);
            sprite.sprite.setCenter(ps.getX() + ps.getWidth()/2, ps.getY() + ps.getHeight()/2);
            sprite.sprite.setRotation(ps.getRotation());

            SpeedComponent speed = Mappers.speed.get(bullet);
            speed.speed = 500f;

            engine.addEntity(bullet);
        }
    }
}
