package com.pd.pong.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.pd.pong.Pong;
import com.pd.pong.model.Ball;
import com.pd.pong.model.GameModel;

import static com.pd.pong.Pong.netmode;
import static java.lang.System.out;


public class GameController implements ContactListener {

    private static final float SCORE_MULT_MOVING = 0.1f;
    private static final float MAX_SCORE = 660f;

    private GameModel model;
    private BatController batController;

    public GameController(GameModel model) {
        this.model = model;
        if (netmode) {
            this.batController = new NetBatController(model);
        } else {
            this.batController = new PlayerBatController(model.getPlayerBat(), model.getWorld());
        }
        this.model.getBall().getBody().applyLinearImpulse(-Ball.SPEED * 0.9f, Ball.SPEED * 1.1f, 0f, 0f, true);
        this.model.getWorld().setContactListener(this);
    }

    public void update(float delta) {
        batController.update(delta);
        if (!BatController.isMoving)
            model.increaseScore(delta);
        else
            model.increaseScore(delta * SCORE_MULT_MOVING);
        if (model.getScore() >= MAX_SCORE) {
            onGameOver();
        }
        Vector2 ballPos = model.getBall().getBody().getPosition();
        if(ballPos.x < 0f || ballPos.x > 20f || ballPos.y < 0 || ballPos.y > 20f){
            onGameOver();
        }
    }

    private void onGameOver() {
        Pong.finalScore = model.getScore();
        Pong.gamestate = Pong.Gamestate.GAMEOVER;
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
