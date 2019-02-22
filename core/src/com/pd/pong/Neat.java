package com.pd.pong;

//import jneat.*;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.evo.NEAT.Environment;
import com.evo.NEAT.Genome;
import com.evo.NEAT.Pool;
import com.pd.pong.controller.NetBatController;

import java.util.ArrayList;

import static java.lang.System.out;

public class Neat implements Environment {

    public Neat() {

    }

    @Override
    public void evaluateFitness(ArrayList<Genome> population) {
        Pong.mode = Pong.Mode.HIDE;
        for (Genome gene : population) {
            NetBatController.gene = gene;
            gene.setFitness(Pong.getScore(gene));
        }
    }

    public static void main(String[] args) {
        out.println("neat");

        Neat neat = new Neat();

        Pool pool = new Pool();
        pool.initializePool();

        Genome topGenome = new Genome();
        int generation = 0;

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 720);
        config.setTitle("Pong");
        Pong pong;
        Lwjgl3Application app;
        Pong.setNetmode(true);
        Pong.mode = Pong.Mode.SHOW;

//        while(true){
        for (int i = 0; i < 5000; i++) {
            pool.evaluateFitness(neat);
            topGenome = pool.getTopGenome();

            System.out.println("TopFitness : " + topGenome.getPoints() + ", Generation : " + generation);

            if (generation % 10 == 0) {
                Pong.mode = Pong.Mode.SHOW;
                Pong.setNetmode(true);
                NetBatController.gene = topGenome;
                pong = new Pong();
                app = new Lwjgl3Application(pong, config);
            }


            if (topGenome.getPoints() > 6000) {
                break;
            }

            pool.breedNewGeneration();
            generation++;

        }
        System.out.println("TopFitness : " + topGenome.getPoints() + ", Generation : " + generation);
        for (int i = 0; i < 1000; i++) {
            Pong.mode = Pong.Mode.SHOW;
            Pong.setNetmode(true);
            NetBatController.gene = topGenome;
            pong = new Pong();
            app = new Lwjgl3Application(pong, config);
        }

        return;
    }

}
