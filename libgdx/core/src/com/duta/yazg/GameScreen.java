package com.duta.yazg;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import static com.duta.yazg.Entities.*;

public class GameScreen extends ScreenAdapter {
    private YAZG game;
    private Texture img;
    private Engine engine;
    private Entity player;
    private Family renderables, enemies;
    private OrthographicCamera cam;

    public GameScreen(YAZG game) {
        this.game = game;

        img = game.assets.get("enemy.png");
        engine = new Engine();
        player = sized(rotatable(renderable()));
        renderables = Family.all(PositionComponent.class, TextureComponent.class).get();
        enemies = Family.all(EnemyComponent.class).get();
        cam = new OrthographicCamera();

        PositionComponent position = Mappers.position.get(player);
        position.x = 50f;
        position.y = 50f;

        TextureComponent texture = Mappers.texture.get(player);
        texture.texture = game.assets.get("player.png");

        SizeComponent size = Mappers.size.get(player);
        size.width  = 32f;
        size.height = 32f;

        engine.addEntity(player);

        cam.setToOrtho(false, game.width, game.height);
    }

    @Override
    public void render(float delta) {
        update();

        centerCamera();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        for(Entity entity : engine.getEntitiesFor(renderables)) {
            Texture texture;
            float x, y, width, height, rotation;

            PositionComponent positionComp = Mappers.position.get(entity);
            x = positionComp.x;
            y = positionComp.y;

            TextureComponent textureComp = Mappers.texture.get(entity);
            texture = textureComp.texture;

            if(Mappers.size.has(entity)) {
                SizeComponent sizeComp = Mappers.size.get(entity);
                width  = sizeComp.width;
                height = sizeComp.height;
            } else {
                width  = textureComp.texture.getWidth();
                height = textureComp.texture.getHeight();
            }
            if(Mappers.rotation.has(entity)) {
                RotationComponent rotationComp = Mappers.rotation.get(entity);
                rotation = rotationComp.rotation;
            } else {
                rotation = 0f;
            }

            game.batch.draw(
                    texture,             // Texture
                    x,                   // X
                    y,                   // Y
                    x +  width/2,        // Origin X
                    y + height/2,        // Origin Y
                    width,               // Width
                    height,              // Height
                    1f,                  // Scale X
                    1f,                  // Scale Y
                    rotation,            // Rotation
                    0,                   // Source X
                    0,                   // Source Y
                    texture.getWidth(),  // Source Width
                    texture.getHeight(), // Source Height
                    false,               // Flip X?
                    false                // Flip Y?
            );
        }
        game.batch.end();
    }

    private void centerCamera() {
        PositionComponent position = Mappers.position.get(player);
        SizeComponent     size     = Mappers.size    .get(player);
        cam.position.set(position.x + size.width/2, position.y + size.height/2, 0);
    }

    private void update() {
        engine.update(Gdx.graphics.getDeltaTime());
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(game.touch);

            Entity enemy = sized(rotatable(renderable(enemy())));

            PositionComponent position = Mappers.position.get(enemy);
            position.x = game.touch.x;
            position.y = game.touch.y;

            TextureComponent texture = Mappers.texture.get(enemy);
            texture.texture = img;

            SizeComponent size = Mappers.size.get(enemy);
            size.width  = 32f;
            size.height = 32f;

            engine.addEntity(enemy);
        }
        for(Entity entity : engine.getEntitiesFor(enemies)) {
            // TODO: Face player
        }
    }
}
