package com.duta.yazg;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class YAZG extends ApplicationAdapter {
	private int width, height;
	private SpriteBatch batch;
	private Engine engine;
	private Texture img;
	private Vector3 touch;
	private OrthographicCamera cam;
	private Family renderable;

	@Override
	public void create() {
		batch = new SpriteBatch();
		engine = new Engine();
		img = new Texture("badlogic.jpg");
		touch = new Vector3();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, this.width = 640, this.height = 480);
		renderable = Family.all(PositionComponent.class, TextureComponent.class).get();
	}

	@Override
	public void render() {
		update();
		redraw();
	}

	private void update() {
		engine.update(Gdx.graphics.getDeltaTime());
		if(Gdx.input.justTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touch);

			EnemyEntity enemy = new EnemyEntity();

			PositionComponent position = Mappers.position.get(enemy);
			position.x = touch.x;
			position.y = touch.y;

			TextureComponent texture = Mappers.texture.get(enemy);
			texture.texture = img;

			SizeComponent size = Mappers.size.get(enemy);
			size.width = 32f;
			size.height = 32f;

			engine.addEntity(enemy);
		}
	}

	private void redraw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for(Entity entity : engine.getEntitiesFor(renderable)) {
			PositionComponent position = Mappers.position.get(entity);
			TextureComponent  texture  = Mappers.texture .get(entity);
			if(Mappers.size.has(entity)) {
				SizeComponent size = Mappers.size.get(entity);
				batch.draw(texture.texture, position.x, position.y, size.width, size.height);
			} else {
				batch.draw(texture.texture, position.x, position.y);
			}
		}
		batch.end();
	}
}
