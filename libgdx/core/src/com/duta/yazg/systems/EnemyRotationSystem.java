package com.duta.yazg.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.duta.yazg.Mappers;
import com.duta.yazg.components.EnemyComponent;
import com.duta.yazg.components.SpriteComponent;

public final class EnemyRotationSystem extends IteratingSystem {
    private final Entity player;

    public EnemyRotationSystem(Entity player) {
        super(Family.all(SpriteComponent.class, EnemyComponent.class).get());
        this.player = player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Sprite ps = Mappers.sprite.get(player).sprite;
        float px = ps.getX() + ps.getWidth()/2;
        float py = ps.getY() + ps.getHeight()/2;

        Sprite es = Mappers.sprite.get(entity).sprite;
        float ex = es.getX() + es.getWidth()/2;
        float ey = es.getY() + es.getHeight()/2;

        es.setRotation(MathUtils.radiansToDegrees * MathUtils.atan2(py - ey, px - ex) - 90);
    }
}
