package com.duta.yazg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.duta.yazg.YAZG;

public class LoadingScreen extends ScreenAdapter {
    private YAZG game;
    private BitmapFont font;

    public LoadingScreen(YAZG game) {
        this.game = game;

        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        if(game.assets.update()) {
            game.setScreen(new GameScreen(game));
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        font.draw(game.batch, String.format("Loading...%.2f%%", game.assets.getProgress() * 100), 0, 0);
        game.batch.end();
    }
}
