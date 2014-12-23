package com.duta.yazg;

import com.badlogic.ashley.core.Entity;

public class EnemyEntity extends Entity {
    public EnemyEntity() {
        add(new PositionComponent());
        add(new TextureComponent());
    }
}
