package com.duta.yazg;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YAZG extends ApplicationAdapter {
	private int width, height;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Engine engine;
	private Texture img;
	private Family renderable;

	@Override
	public void create() {
		cam = new OrthographicCamera();
		batch = new SpriteBatch();
		engine = new Engine();
		img = new Texture("badlogic.jpg");
		renderable = Family.all(PositionComponent.class, TextureComponent.class).get();
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(true, this.width = width, this.height = height);
	}

	@Override
	public void render() {
		update();
		redraw();
	}

	private void update() {
		engine.update(Gdx.graphics.getDeltaTime());
		if(Gdx.input.justTouched()) {
			EnemyEntity enemy = new EnemyEntity();
			PositionComponent position = Mappers.position.get(enemy);
			position.x = Gdx.input.getX();
			position.y = Gdx.input.getY();
			TextureComponent texture = Mappers.texture.get(enemy);
			texture.texture = img;
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
			batch.draw(texture.texture, position.x, position.y, 32, 32);
		}
		batch.end();
	}
}
