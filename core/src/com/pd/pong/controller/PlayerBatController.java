package com.pd.pong.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;
import com.pd.pong.model.Bat;

public class PlayerBatController extends BatController {

    public PlayerBatController(Bat bat, World world) {
        super(bat);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        float dir = 0f;
        isMoving = false;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dir += 1f;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dir += -1f;
            isMoving = true;
        }
        bat.getBody().setLinearVelocity(0f, SPEED * dir);
    }

}
