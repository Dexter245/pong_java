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
import com.evo.NEAT.Genome;
import com.pd.pong.controller.GameController;
import com.pd.pong.controller.NetBatController;
import com.pd.pong.model.GameModel;
import com.pd.pong.view.DefaultRenderer;
import com.pd.pong.view.NullRenderer;
import com.pd.pong.view.Renderer;
import org.lwjgl.openal.AL;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Pong extends ApplicationAdapter {

    public enum Gamestate {
        RUNNING,
        GAMEOVER
    }

    public enum Mode {
        //        PLAY,
        SHOW,
        HIDE
    }

    public static Gamestate gamestate = Gamestate.RUNNING;
    public static Mode mode = Mode.SHOW;
//    public static Mode mode = Mode.HIDE;
    public static boolean netmode = false;
    public static float finalScore = -1f;

    private GameModel model;
    private GameController controller;
    private Renderer renderer;
    private World world;

    public static float getScore(Genome gene) {


        Pong pong = new Pong();
        Pong.setNetmode(true);
        NetBatController.gene = gene;
        pong.create();
        while (Pong.gamestate != Pong.Gamestate.GAMEOVER)
            pong.render();
        float score = Pong.finalScore;
        pong.dispose();
        return score;
    }

    public Pong() {
        Pong.gamestate = Gamestate.RUNNING;
        Pong.finalScore = -1f;
    }

    @Override
    public void create() {
//        out.println("pong create in thread: " + Thread.currentThread());

        Box2D.init();
        world = new World(new Vector2(0, 0), true);

        model = new GameModel(world);
        if (netmode) {
            if (mode == Mode.SHOW) {
                renderer = new DefaultRenderer(model);
            } else if (mode == Mode.HIDE) {
                renderer = new NullRenderer();
            }
        } else {
            renderer = new DefaultRenderer(model);
        }
        controller = new GameController(model);


    }

    @Override
    public void render() {
//        out.println("pong render");
        float delta;
        if (netmode && mode == Mode.HIDE) {
            delta = 1 / 60f;
        } else {
            delta = Gdx.graphics.getDeltaTime();
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }

        if(gamestate != Gamestate.GAMEOVER){
            controller.update(delta);
            world.step(1 / 60f, 10, 10);
            renderer.render(delta);
        }else{
//            dispose();
            Gdx.app.exit();
//            exit(0);
//            dispose();
        }



    }

    public static void setNetmode(boolean netmode) {
        Pong.netmode = netmode;
    }

    @Override
    public void dispose() {
//        out.println("pong dispose");
//        if(world != null)
        world.dispose();
    }
}
