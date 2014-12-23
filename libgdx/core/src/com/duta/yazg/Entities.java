package com.duta.yazg;

import com.badlogic.ashley.core.Entity;

public final class Entities {
    private Entities() {}

    // Create entities

    public static Entity basic() {
        return new Entity();
    }

    public static Entity sized() {
        return sized(basic());
    }

    public static Entity renderable() {
        return renderable(basic());
    }

    // Modify entities

    public static Entity sized(Entity entity) {
        entity.add(new SizeComponent());
        return entity;
    }

    public static Entity renderable(Entity entity) {
        entity.add(new PositionComponent());
        entity.add(new TextureComponent());
        return entity;
    }
}
