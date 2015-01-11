package com.duta.yazg;

import com.badlogic.ashley.core.Entity;
import com.duta.yazg.components.BulletComponent;
import com.duta.yazg.components.EnemyComponent;
import com.duta.yazg.components.SpeedComponent;
import com.duta.yazg.components.SpriteComponent;

public final class Entities {
    private Entities() {}

    // Create entities

    public static Entity basic() {
        return new Entity();
    }

    public static Entity enemy() {
        return enemy(basic());
    }

    public static Entity bullet() {
        return bullet(basic());
    }

    public static Entity sprite() {
        return sprite(basic());
    }

    public static Entity speedy() {
        return speedy(basic());
    }

    // Modify entities

    public static Entity enemy(Entity entity) {
        return entity.add(new EnemyComponent());
    }

    public static Entity bullet(Entity entity) {
        return entity.add(new BulletComponent());
    }

    public static Entity sprite(Entity entity) {
        return entity.add(new SpriteComponent());
    }

    public static Entity speedy(Entity entity) {
        return entity.add(new SpeedComponent());
    }
}
