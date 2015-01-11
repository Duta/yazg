package com.duta.yazg.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.duta.yazg.Mappers;
import com.duta.yazg.components.BulletComponent;
import com.duta.yazg.components.EnemyComponent;
import com.duta.yazg.components.SpeedComponent;
import com.duta.yazg.components.SpriteComponent;

import static com.badlogic.gdx.math.MathUtils.*;

public class BulletSystem extends IteratingSystem {
    private Engine engine;
    private final Family enemies;

    public BulletSystem() {
        super(Family.all(BulletComponent.class, SpriteComponent.class, SpeedComponent.class).get());
        enemies = Family.all(EnemyComponent.class).get();
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        this.engine = null;
    }

    @Override
    protected void processEntity(Entity bullet, float deltaTime) {
        Sprite sprite = Mappers.sprite.get(bullet).sprite;
        float  speed  = Mappers.speed .get(bullet).speed;

        float rotation = -degreesToRadians * sprite.getRotation();

        float dx = deltaTime * speed * sin(rotation);
        float dy = deltaTime * speed * cos(rotation);

        float sx = sprite.getX();
        float sy = sprite.getY();

        for(Entity enemy : engine.getEntitiesFor(enemies)) {
            // TODO: Check if hits enemy
        }

        sprite.translate(dx, dy);
    }
}
