package com.pd.pong.controller;

import com.pd.pong.model.GameModel;

import java.lang.System.*;

import static java.lang.System.out;


public class GameController {

    private GameModel model;

    public GameController(GameModel model){
        this.model = model;
    }

    public void update(float delta){
//        out.println("controller update");
    }

}
