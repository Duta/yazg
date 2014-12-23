package com.duta.yazg;

import com.badlogic.ashley.core.ComponentMapper;

public final class Mappers {
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<TextureComponent>  texture  = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<SizeComponent>     size     = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<EnemyComponent>    enemy    = ComponentMapper.getFor(EnemyComponent.class);
    public static final ComponentMapper<RotationComponent> rotation = ComponentMapper.getFor(RotationComponent.class);

    private Mappers() {}
}
