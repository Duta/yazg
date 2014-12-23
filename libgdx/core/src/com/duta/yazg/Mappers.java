package com.duta.yazg;

import com.badlogic.ashley.core.ComponentMapper;

public final class Mappers {
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);

    private Mappers() {}
}
