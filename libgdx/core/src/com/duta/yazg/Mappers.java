package com.duta.yazg;

import com.badlogic.ashley.core.ComponentMapper;

public final class Mappers {
    public static final ComponentMapper<SpriteComponent> sprite = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<EnemyComponent>  enemy  = ComponentMapper.getFor(EnemyComponent.class);
    public static final ComponentMapper<SpeedComponent>  speed  = ComponentMapper.getFor(SpeedComponent.class);

    private Mappers() {}
}
