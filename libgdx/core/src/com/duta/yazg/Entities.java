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

    public static Entity sized() {
        return sized(basic());
    }

    public static Entity rotatable() {
        return rotatable(basic());
    }

    public static Entity renderable() {
        return renderable(basic());
    }

    // Modify entities

    public static Entity enemy(Entity entity) {
        return entity
                .add(new EnemyComponent());
    }

    public static Entity sized(Entity entity) {
        return entity
                .add(new SizeComponent());
    }

    public static Entity rotatable(Entity entity) {
        return entity
                .add(new RotationComponent());
    }

    public static Entity renderable(Entity entity) {
        return entity
                .add(new PositionComponent())
                .add(new TextureComponent());
    }
}
