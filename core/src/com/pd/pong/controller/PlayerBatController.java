package com.pd.pong.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.pd.pong.model.Bat;

import static java.lang.System.out;

public class PlayerBatController extends BatController {

    public static final float SPEED = 3.0f;

    public PlayerBatController(Bat bat, World world) {
        super(bat);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        float dir = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            dir += 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            dir += -1f;
        bat.getBody().setLinearVelocity(0f, SPEED * dir);
    }

}
