package com.duta.yazg.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.duta.yazg.Mappers;
import com.duta.yazg.YAZG;
import com.duta.yazg.components.SpeedComponent;
import com.duta.yazg.components.SpriteComponent;

import static com.duta.yazg.Entities.*;

public final class SpawnOnTouchSystem extends EntitySystem {
    private final YAZG game;
    private final Camera cam;
    private Engine engine;

    public SpawnOnTouchSystem(YAZG game, Camera cam) {
        this.game = game;
        this.cam = cam;
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
        // On touch, add an enemy
        if(Gdx.input.justTouched()) {
            // Grab the touch position
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert to world coordinates
            cam.unproject(game.touch);

            Entity enemy = speedy(enemy(sprite()));

            SpriteComponent sprite = Mappers.sprite.get(enemy);
            sprite.sprite = new Sprite(game.assets.<Texture>get("enemy.png"));
            sprite.sprite.setSize(32f, 32f);
            sprite.sprite.setOrigin(16f, 16f);
            sprite.sprite.setCenter(game.touch.x, game.touch.y);

            SpeedComponent speed = Mappers.speed.get(enemy);
            speed.speed = 25f;

            engine.addEntity(enemy);
        }
    }
}
