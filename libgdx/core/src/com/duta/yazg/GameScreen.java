package com.duta.yazg;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

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

        engine.addSystem(new EnemyMovementSystem());
        engine.addEntity(player);

        cam.setToOrtho(false, game.width, game.height);
    }

    @Override
    public void render(float delta) {
        // Process entity systems
        engine.update(delta);

        // General update
        update(delta);

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

    private void update(float delta) {
        // On touch, add an enemy
        if(Gdx.input.justTouched()) {
            // Grab the touch position
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert to world coordinates
            cam.unproject(game.touch);

            Entity enemy = speedy(enemy(sprite()));

            SpriteComponent sprite = Mappers.sprite.get(enemy);
            sprite.sprite = new Sprite(game.assets.<Texture>get("enemy.png"));
            sprite.sprite.setSize(32f, 32f);
            sprite.sprite.setOrigin(16f, 16f);
            sprite.sprite.setCenter(game.touch.x, game.touch.y);

            SpeedComponent speed = Mappers.speed.get(enemy);
            speed.speed = 25f;

            engine.addEntity(enemy);
        }

        // Grab a reference to the player sprite
        Sprite ps = Mappers.sprite.get(player).sprite;

        // Handle player movement input
        float pspeed = Mappers.speed.get(player).speed * delta;
        int pxd = 0;
        int pyd = 0;
        if(Gdx.input.isKeyPressed(Keys.UP))    pyd++;
        if(Gdx.input.isKeyPressed(Keys.DOWN))  pyd--;
        if(Gdx.input.isKeyPressed(Keys.LEFT))  pxd--;
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) pxd++;
        if(pxd > 0) ps.setRotation(270f);
        if(pxd < 0) ps.setRotation( 90f);
        if(pyd < 0) ps.setRotation(180f + pxd*45f);
        if(pyd > 0) ps.setRotation(  0f - pxd*45f);
        ps.translate(pxd * pspeed, pyd * pspeed);

        // Make all enemies face the player
        float px = ps.getX();
        float py = ps.getY();
        for(Entity entity : engine.getEntitiesFor(enemies)) {
            Sprite es = Mappers.sprite.get(entity).sprite;
            float ex = es.getX();
            float ey = es.getY();
            es.setRotation(MathUtils.radiansToDegrees * MathUtils.atan2(py - ey, px - ex) - 90);
        }
    }
}
