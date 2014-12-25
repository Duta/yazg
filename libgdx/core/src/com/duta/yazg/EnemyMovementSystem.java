package com.duta.yazg;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class EnemyMovementSystem extends IteratingSystem {
    public EnemyMovementSystem() {
        super(Family.all(SpriteComponent.class, SpeedComponent.class, EnemyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Sprite sprite = Mappers.sprite.get(entity).sprite;
        float  speed  = Mappers.speed .get(entity).speed;

        float rotation = -MathUtils.degreesToRadians * sprite.getRotation();

        float dx = deltaTime * speed * MathUtils.sin(rotation);
        float dy = deltaTime * speed * MathUtils.cos(rotation);

        sprite.translate(dx, dy);
    }
}
