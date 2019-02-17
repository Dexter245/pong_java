package com.pd.pong.view;

import com.pd.pong.model.GameModel;

import static java.lang.System.out;

public class DefaultRenderer implements Renderer {

    private GameModel model;

    public DefaultRenderer(GameModel model){
        this.model = model;
    }

    @Override
    public void render(float delta) {
//        out.println("defaultRenderer render");
    }

}
