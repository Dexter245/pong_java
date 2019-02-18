package com.pd.pong.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Ball {

    private Body body;
    private Fixture fixture;

    public Ball(World world, Vector2 pos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pos);
        bodyDef.fixedRotation = true;

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.25f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;
        fixtureDef.shape = shape;

        fixture = body.createFixture(fixtureDef);
    }

    public Body getBody() {
        return body;
    }
}
