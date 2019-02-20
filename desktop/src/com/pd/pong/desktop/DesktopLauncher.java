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
//        netmode = true;
        if(netmode){
//            for(int i = 0; i < 10000; i++){
//                Pong.getScore();
//            }
        }else{
            out.println("netmode off");
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setWindowedMode(1280, 720);
            config.setTitle("Pong");

            Pong pong;
            Lwjgl3Application app;

            for(int i = 0; i < 5; i++){
                pong = new Pong();
                app = new Lwjgl3Application(pong, config);
                float score = Pong.finalScore;
                out.println("finalScore: " + score);
            }
        }
	}
}
