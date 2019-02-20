package com.pd.pong.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.pd.pong.model.GameModel;

import static java.lang.System.out;

public class DefaultRenderer implements Renderer {

    private GameModel model;
    private Box2DDebugRenderer debugRenderer;
    private Camera camera;
    private Camera camera2;

    private SpriteBatch batch;
    private BitmapFont font;

    public DefaultRenderer(GameModel model) {
        this.model = model;
        debugRenderer = new Box2DDebugRenderer();

        float width = 10;
        float height = 10f / (1280f / 720f);
        float x = width / 2f;
        float y = height / 2f;
        camera = new OrthographicCamera(width, height);
        camera.translate(x, y, 0f);
        camera.update();
//        out.println("width: " + width + ", height. " + height + ", x: " + x + ", y: " + y);
//        out.println("camera.position: " + camera.position);

        camera2 = new OrthographicCamera(1280, 720);
        camera2.update();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera2.combined);
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
//        out.println("defaultRenderer render");
        batch.begin();
        float score = Math.round(model.getScore()*10)/10f;
        font.draw(batch, "Score: " + score, 0f, 0f);
        batch.end();

        debugRenderer.render(model.getWorld(), camera.combined);

    }

}
