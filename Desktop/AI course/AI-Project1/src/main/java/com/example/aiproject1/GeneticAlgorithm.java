package com.example.aiproject1;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiConsumer;

public class GeneticAlgorithm {
    private int populationSize;
    private double mutationRate;
    private int[] targetPasscode;
    private ArrayList<Chromosome> population;
    private int generationCount = 0;
    private BiConsumer<Integer, Integer> updateUI;

    public GeneticAlgorithm(int popSize, double mutRate) {
        this.populationSize = popSize;
        this.mutationRate = mutRate;
    }

    public void setOnUpdateUI(BiConsumer<Integer, Integer> callback) { this.updateUI = callback; }

    public void run() {
        targetPasscode = generateRandomPasscode(32);
        initializePopulation();

        boolean found = false;
        while (!found && generationCount < 200) { // So it doesn't get stuck STOP
            generationCount++;
            for (Chromosome c : population) {
                c.calculateFitness(targetPasscode);
                if (c.getFitness() == 32) found = true;
            }

            int bestFitness = getBestFitness();
            if (updateUI != null) updateUI.accept(generationCount, bestFitness);

            if (!found) evolve();
        }
    }

    private int[] generateRandomPasscode(int length) {
        int[] passcode = new int[length];
        Random r = new Random();
        for (int i = 0; i < length; i++) passcode[i] = r.nextBoolean() ? 1 : 0;
        return passcode;
    }

    private void initializePopulation() {
        population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) population.add(new Chromosome(32));
    }

    private void evolve() {
        ArrayList<Chromosome> nextGeneration = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome p1 = tournamentSelection();
            Chromosome p2 = tournamentSelection();
            Chromosome child = p1.crossover(p2);
            child.mutate(mutationRate);
            nextGeneration.add(child);
        }
        population = nextGeneration;
    }

    private Chromosome tournamentSelection() {
        Random r = new Random();
        Chromosome best = null;
        for (int i = 0; i < 5; i++) {
            Chromosome ind = population.get(r.nextInt(population.size()));
            if (best == null || ind.getFitness() > best.getFitness()) best = ind;
        }
        return best;
    }

    public int getBestFitness() {
        int best = 0;
        for (Chromosome c : population) if (c.getFitness() > best) best = c.getFitness();
        return best;
    }
}