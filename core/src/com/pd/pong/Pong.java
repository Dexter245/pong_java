package com.pd.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.pd.pong.controller.GameController;
import com.pd.pong.model.GameModel;
import com.pd.pong.view.DefaultRenderer;
import com.pd.pong.view.Renderer;

import static java.lang.System.out;

public class Pong extends ApplicationAdapter {

    private GameModel model;
    private GameController controller;
    private Renderer renderer;

    private World world;

    @Override
    public void create() {

        Box2D.init();
        world = new World(new Vector2(0, 0), true);

        model = new GameModel(world);
        controller = new GameController(model);
        renderer = new DefaultRenderer(model);


    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.update(delta);
        renderer.render(delta);

        world.step(1 / 60f, 10, 10);
    }

    @Override
    public void dispose() {
        world.dispose();

    }
}
