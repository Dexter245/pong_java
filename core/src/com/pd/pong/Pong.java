package com.pd.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pd.pong.controller.GameController;
import com.pd.pong.model.GameModel;
import com.pd.pong.view.DefaultRenderer;
import com.pd.pong.view.Renderer;

public class Pong extends ApplicationAdapter {

    private GameModel model;
    private GameController controller;
    private Renderer renderer;

	@Override
	public void create () {
	    this.model = new GameModel();
	    this.controller = new GameController(this.model);
	    this.renderer = new DefaultRenderer(this.model);
	}

	@Override
	public void render () {
	    float delta = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		renderer.render(delta);
	}
	
	@Override
	public void dispose () {

	}
}
