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

    public Neat(){

    }

    @Override
    public void evaluateFitness(ArrayList<Genome> population) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 720);
        config.setTitle("Pong");
        Pong pong;
        Lwjgl3Application app;
        Pong.setNetmode(true);
        Pong.mode = Pong.Mode.SHOW;
        for (Genome gene: population) {
            NetBatController.gene = gene;
            if(Pong.mode == Pong.Mode.SHOW){
                pong = new Pong();
                app = new Lwjgl3Application(pong, config);
                float score = Pong.finalScore;
                Pong.mode = Pong.Mode.HIDE;
                gene.setFitness(Pong.finalScore);
            }else if(Pong.mode == Pong.Mode.HIDE){
                gene.setFitness(Pong.getScore(gene));
            }

        }

    }

    public static void main(String[] args) {
        out.println("neat");

        Neat neat = new Neat();

        Pool pool = new Pool();
        pool.initializePool();

        Genome topGenome = new Genome();
        int generation = 0;
//        while(true){
        for(int i = 0; i < 5000; i++){
            pool.evaluateFitness(neat);
            topGenome = pool.getTopGenome();
            System.out.println("TopFitness : " + topGenome.getPoints() + ", Generation : " + generation);

            if(topGenome.getPoints()>600){
                break;
            }
//            System.out.println("Population : " + pool.getCurrentPopulation() );
            //           System.out.println("Total number of matches played : "+TicTacToe.matches);
            //           pool.calculateGenomeAdjustedFitness();

            pool.breedNewGeneration();
            generation++;

        }
//        System.out.println(topGenome.evaluateNetwork(new float[]{1,0})[0]);

        return;
    }

}
