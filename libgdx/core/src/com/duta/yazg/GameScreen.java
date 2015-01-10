package com.duta.yazg;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.duta.yazg.Entities.*;

public class GameScreen extends ScreenAdapter {
    private YAZG game;
    private Engine engine;
    private Entity player;
    private Family sprites, enemies;
    private OrthographicCamera cam;

    public GameScreen(YAZG game) {
        this.game = game;

        engine = new Engine();
        player = speedy(sprite());
        sprites = Family.all(SpriteComponent.class).get();
        enemies = Family.all(EnemyComponent.class).get();
        cam = new OrthographicCamera();

        SpriteComponent sprite = Mappers.sprite.get(player);
        sprite.sprite = new Sprite(game.assets.<Texture>get("player.png"));
        sprite.sprite.setOrigin(16f, 16f);
        sprite.sprite.setSize(32f, 32f);

        SpeedComponent speed = Mappers.speed.get(player);
        speed.speed = 60f;

        engine.addSystem(new SpawnOnTouchSystem(game, cam, engine));
        engine.addSystem(new EnemyMovementSystem());
        engine.addSystem(new EnemyRotationSystem(player));
        engine.addSystem(new PlayerMovementSystem(player));
        engine.addEntity(player);

        cam.setToOrtho(false, game.width, game.height);
    }

    @Override
    public void render(float delta) {
        // Process entity systems
        engine.update(delta);

        // Process the camera
        centerCamera();
        cam.update();

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw everything
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        for(Entity entity : engine.getEntitiesFor(sprites)) {
            Mappers.sprite.get(entity).sprite.draw(game.batch);
        }
        game.batch.end();
    }

    private void centerCamera() {
        Sprite sprite = Mappers.sprite.get(player).sprite;
        cam.position.set(sprite.getX() + sprite.getWidth()/2, sprite.getY() + sprite.getHeight()/2, 0);
    }
}
