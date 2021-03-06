package com.duta.yazg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.duta.yazg.screens.LoadingScreen;

public final class YAZG extends Game {
	public int width, height;
	public SpriteBatch batch;
	public Vector3 touch;
	public AssetManager assets;
	public ControlSettings controls;

	@Override
	public void create() {
		width  = 640;
		height = 480;
		batch = new SpriteBatch();
		touch = new Vector3();
		controls = new ControlSettings();
		assets = new AssetManager();
		assets.load("bullet.png", Texture.class);
		assets.load("enemy.png",  Texture.class);
		assets.load("player.png", Texture.class);
		setScreen(new LoadingScreen(this));
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
