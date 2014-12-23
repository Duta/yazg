package com.duta.yazg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class YAZG extends Game {
	public int width, height;
	public SpriteBatch batch;
	public Vector3 touch;

	@Override
	public void create() {
		width  = 640;
		height = 480;
		batch = new SpriteBatch();
		touch = new Vector3();
		setScreen(new GameScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
