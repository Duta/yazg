package com.duta.yazg.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.duta.yazg.Mappers;
import com.duta.yazg.components.EnemyComponent;
import com.duta.yazg.components.SpeedComponent;
import com.duta.yazg.components.SpriteComponent;

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
