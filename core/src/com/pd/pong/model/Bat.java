package com.pd.pong.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static java.lang.System.out;

public class Bat {

    private static final float WIDTH = 0.1f;
    private static final float HEIGHT = 0.75f;

    private World world;
    private Body body;
    private Fixture fixture;

    public Bat(World world, Vector2 pos) {
        this.world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;
        fixtureDef.shape = shape;

        fixture = body.createFixture(fixtureDef);

    }

    public void update(float delta) {
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        if (y <= HEIGHT) {
            body.setTransform(x, HEIGHT, 0f);
        } else if (y >= GameModel.WORLD_HEIGHT - HEIGHT) {
            body.setTransform(x, GameModel.WORLD_HEIGHT - HEIGHT, 0f);
        }
    }

    public Body getBody() {
        return body;
    }

}
