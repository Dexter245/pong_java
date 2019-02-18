package com.pd.pong.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.pd.pong.model.Ball;
import com.pd.pong.model.GameModel;

import java.lang.System.*;

import static java.lang.System.out;


public class GameController implements ContactListener {

    private static final float BALL_SPEED = 1.0f;

    private GameModel model;
    private BatController batController;

    public GameController(GameModel model) {
        this.model = model;
        this.batController = new PlayerBatController(model.getPlayerBat(), model.getWorld());
        this.model.getBall().getBody().applyLinearImpulse(-BALL_SPEED, BALL_SPEED, 0f, 0f, true);
        this.model.getWorld().setContactListener(this);
    }

    public void update(float delta) {
//        out.println("controller update");
        batController.update(delta);
        model.increaseScore(delta);
    }

    private void onGameOver() {
        out.println("GAMEOVER");
        float finalScore = model.getScore();
        out.println("finalScore: " + finalScore);
        System.exit(0);
    }

    @Override
    public void beginContact(Contact contact) {
        Body b1 = contact.getFixtureA().getBody();
        Body b2 = contact.getFixtureB().getBody();
        Body ball = model.getBall().getBody();
        Body wallLeft = GameModel.Walls.wallLeft;
        if ((b1.equals(ball) || b2.equals(ball)) &&
                (b1.equals(wallLeft) || b2.equals(wallLeft))) {
            onGameOver();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
