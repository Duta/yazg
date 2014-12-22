package com.duta.yazg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class YAZG extends ApplicationAdapter {
	private int width, height;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Array<Vector2> enemyPositions;
	private Texture img;
	
	@Override
	public void create() {
		cam = new OrthographicCamera();
		batch = new SpriteBatch();
		enemyPositions = new Array<Vector2>();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, this.width = width, this.height = height);
	}

	@Override
	public void render() {
		update();
		redraw();
	}

	private void update() {
		if(Gdx.input.isTouched()) {
			enemyPositions.add(new Vector2(MathUtils.random(width), MathUtils.random(height)));
		}
	}

	private void redraw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for(Vector2 enemyPosition : enemyPositions) {
			batch.draw(img, enemyPosition.x, enemyPosition.y, 16, 16);
		}
		batch.end();
	}
}
