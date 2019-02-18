package com.pd.pong.controller;

import com.pd.pong.model.Bat;

public abstract class BatController {

    protected Bat bat;

    public BatController(Bat bat) {
        this.bat = bat;
    }

    public void update(float delta) {
        bat.update(delta);
    }
}
