package com.pd.pong.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.pd.pong.Pong;

import static java.lang.System.out;

public class DesktopLauncher {
	public static void main (String[] arg) {
	    out.println("DesktopLauncher");
	    boolean netmode = false;
        out.println("netmode off");
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 720);
        config.setTitle("Pong");

        Pong pong = new Pong();
        Lwjgl3Application app = new Lwjgl3Application(pong, config);
        float score = Pong.finalScore;
        out.println("\nFINAL SCORE: " + score);
	}
}
