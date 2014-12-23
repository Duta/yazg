package com.duta.yazg;

import com.badlogic.ashley.core.Entity;

public final class Entities {
    private Entities() {}

    // Create entities

    public static Entity basic() {
        return new Entity();
    }

    public static Entity enemy() {
        return enemy(basic());
    }

    public static Entity sprite() {
        return sprite(basic());
    }

    // Modify entities

    public static Entity enemy(Entity entity) {
        return entity
                .add(new EnemyComponent());
    }


    public static Entity sprite(Entity entity) {
        return entity
                .add(new SpriteComponent());
    }
}
