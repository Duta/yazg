package com.duta.yazg;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class EnemyRotationSystem extends IteratingSystem {
    private Entity player;

    public EnemyRotationSystem(Entity player) {
        super(Family.all(SpriteComponent.class, EnemyComponent.class).get());
        this.player = player;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Sprite ps = Mappers.sprite.get(player).sprite;
        float px = ps.getX();
        float py = ps.getY();

        Sprite es = Mappers.sprite.get(entity).sprite;
        float ex = es.getX();
        float ey = es.getY();

        es.setRotation(MathUtils.radiansToDegrees * MathUtils.atan2(py - ey, px - ex) - 90);
    }
}
