package com.example.aiproject1;

import java.util.Random;

public class Chromosome {

    private int[] genes; // 32-bit solution
    private int fitness; // number of matching bits

    // Constructor: random chromosome
    public Chromosome(int length) {
        genes = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            genes[i] = random.nextBoolean() ? 1 : 0;
        }
        fitness = 0;
    }

    // Constructor: from given genes (used after crossover)
    public Chromosome(int[] genes) {
        this.genes = genes.clone();
        this.fitness = 0;
    }

    // Calculate fitness by comparing with passcode
    public void calculateFitness(int[] passcode) {
        int score = 0;

        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == passcode[i]) {
                score++;
            }
        }
        this.fitness = score;
    }

    public int getFitness() {
        return fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    // Mutation: flip bits with given probability
    public void mutate(double mutationRate) {
        Random random = new Random();

        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                genes[i] = (genes[i] == 0) ? 1 : 0; // flip the bit
            }
        }
    }

    public Chromosome crossover(Chromosome partner) {
        int[] childGenes = new int[32];
        Random random = new Random();

        // pick random point for crossover
        int midpoint = random.nextInt(32);

        // combine parents genes
        for (int i = 0; i < 32; i++) {
            if (i < midpoint) {
                childGenes[i] = this.genes[i]; // from current parent
            } else {
                childGenes[i] = partner.getGenes()[i]; // from the other parent
            }
        }

        return new Chromosome(childGenes);
    }
}
