package com.duta.yazg;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
    private YAZG game;
    private Texture img;
    private Engine engine;
    private OrthographicCamera cam;
    private Family renderable;

    public GameScreen(YAZG game) {
        this.game = game;

        engine = new Engine();
        img = new Texture("badlogic.jpg");
        cam = new OrthographicCamera();
        cam.setToOrtho(false, game.width, game.height);
        renderable = Family.all(PositionComponent.class, TextureComponent.class).get();
    }

    @Override
    public void render(float delta) {
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        for(Entity entity : engine.getEntitiesFor(renderable)) {
            PositionComponent position = Mappers.position.get(entity);
            TextureComponent  texture  = Mappers.texture .get(entity);
            if(Mappers.size.has(entity)) {
                SizeComponent size = Mappers.size.get(entity);
                game.batch.draw(texture.texture, position.x, position.y, size.width, size.height);
            } else {
                game.batch.draw(texture.texture, position.x, position.y);
            }
        }
        game.batch.end();
    }

    private void update() {
        engine.update(Gdx.graphics.getDeltaTime());
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(game.touch);

            EnemyEntity enemy = new EnemyEntity();

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
    }
}
