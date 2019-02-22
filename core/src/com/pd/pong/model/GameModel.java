package com.pd.pong.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static java.lang.System.out;

public class GameModel {

    public static final float WORLD_WIDTH = 10;
    public static final float WORLD_HEIGHT = 10f / (1280f / 720f);
    private static final float WALL_WIDTH = 1.0f;

    private World world;
    private Ball ball;
    private Bat playerBat;
    private float score = 0f;

    public static class Walls {
        public static Body wallLeft;
        public static Body wallRight;
        public static Body wallTop;
        public static Body wallBottom;
    }

    public GameModel(World world) {
        this.world = world;
//        ball = new Ball(world, new Vector2(5, 3));
        float posx = (float) Math.random() * 6 + 3;
        float posy = (float) Math.random() * 5 + 0;
        ball = new Ball(world, new Vector2(posx, posy));
        playerBat = new Bat(world, new Vector2(0.5f, WORLD_HEIGHT / 2f));

        //wallBottom
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(5, -WALL_WIDTH);
        Walls.wallBottom = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5f, WALL_WIDTH);
        Fixture fixture = Walls.wallBottom.createFixture(shape, 1f);
        //wallTop
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(5, WORLD_HEIGHT + WALL_WIDTH*1.0f);
        Walls.wallTop = world.createBody(bodyDef);
        shape = new PolygonShape();
        shape.setAsBox(5f, WALL_WIDTH);
        fixture = Walls.wallTop.createFixture(shape, 1f);
        //wallLeft
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(-WALL_WIDTH, WORLD_HEIGHT / 2f);
        Walls.wallLeft = world.createBody(bodyDef);
        shape = new PolygonShape();
        shape.setAsBox(WALL_WIDTH, WORLD_HEIGHT / 2f);
        fixture = Walls.wallLeft.createFixture(shape, 1f);
        //wallRight
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(WORLD_WIDTH + WALL_WIDTH, WORLD_HEIGHT / 2f);
        Walls.wallRight = world.createBody(bodyDef);
        shape = new PolygonShape();
        shape.setAsBox(WALL_WIDTH, WORLD_HEIGHT / 2f);
        fixture = Walls.wallRight.createFixture(shape, 1f);
    }

    public void increaseScore(float amount) {
        score += amount;
    }

    public void decreaseScore(float amount) {
        score -= amount;
    }

    public float getScore() {
        return score;
    }

    public World getWorld() {
        return world;
    }

    public Ball getBall() {
        return ball;
    }

    public Bat getPlayerBat() {
        return playerBat;
    }
}
