package com.pd.pong.controller;

import com.evo.NEAT.Genome;
import com.pd.pong.model.Bat;
import com.pd.pong.model.GameModel;

import java.lang.reflect.Array;

import static java.lang.System.out;

public class NetBatController extends BatController {

    public static Genome gene;
    private GameModel model;

    public NetBatController(GameModel model) {
        super(model.getPlayerBat());
        this.model = model;
    }

    @Override
    public void update(float delta) {
//        out.println("netbatcontroller update");
        super.update(delta);
        float batPosY = model.getPlayerBat().getBody().getPosition().y;
        float ballPosX = model.getBall().getBody().getPosition().x;
        float ballPosY = model.getBall().getBody().getPosition().y;
        float[] input = {batPosY, ballPosX, ballPosY};
        float[] output = NetBatController.gene.evaluateNetwork(input);
        int maxIndex = 0;
        float max = output[0];
        if(output[1] > max){
            max = output[1];
            maxIndex = 1;
        }
        if(output[2] > max){
            max = output[2];
            maxIndex = 2;
        }

//        out.print("input: ");
//        for(float in : input)
//            out.print(in + ", ");
//        out.print("output: ");
//        for(float o : output)
//            out.print(o + ", ");
//        out.println("maxIndex: " + maxIndex + ", max: " + max);


        float dir = 0f;
        if(maxIndex == 0){
            dir = 0f;
        }else if(maxIndex == 1){
            dir = 1f;
        }else if(maxIndex == 2){
            dir = -1f;
        }
//        out.println("dir: " + dir);
        bat.getBody().setLinearVelocity(0f, PlayerBatController.SPEED * dir);
    }
}
