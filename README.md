<p align="center">
<img src="Desktop/AI course/AI-Project1/screenshots/logo_1.png" alt="Breaking a Password Using Genetic Algorithm Logo" width="160">
</p>

# Breaking a Password Using Genetic Algorithm

A Java application that uses **Genetic Algorithm** to evolve and find a randomly generated 32-bit binary passcode. This is an educational AI project demonstrating core concepts of evolutionary algorithms through an interactive JavaFX GUI.

## 📋 Project Overview

This project implements a **Genetic Algorithm** that simulates the process of evolution to solve the problem of breaking a randomly generated password. Instead of brute-forcing all possible combinations, the algorithm evolves a population of candidate solutions over multiple generations, using selection, crossover, and mutation operations to progressively improve fitness.

### What Problem Does It Solve?

Given a randomly generated 32-bit binary passcode, the genetic algorithm attempts to find the exact passcode by:
- Starting with a random population of candidate solutions
- Evaluating fitness (how many bits match the target)
- Selecting the best candidates to reproduce
- Creating offspring through crossover and mutation
- Repeating until the target is found

## 🎬 Screenshots

**Splash Screen**

![Splash Screen](Desktop/AI%20course/AI-Project1/screenshots/splash-screen.png)

**Simulation Window (Before Running)**

![App Empty](Desktop/AI%20course/AI-Project1/screenshots/app-empty.png)

**Algorithm Running — Fitness Convergence**

![App Running](Desktop/AI%20course/AI-Project1/screenshots/app-running.png)

**Success Report**

![Success Report](Desktop/AI%20course/AI-Project1/screenshots/success-report.png)

## ✨ Features

- **Interactive GUI** - Built with JavaFX with a sleek terminal-inspired design
- **Real-time Visualization** - Watch the algorithm evolve across generations
- **Configurable Parameters** - Adjust population size and mutation rate
- **Performance Statistics** - Track convergence data and best fitness scores
- **Generation Tracking** - Monitor progress generation-by-generation
- **Elegant UI Design** - Green-on-black terminal aesthetic with smooth animations

## 🛠️ Project Structure

```
AI-Project1/
├── src/main/java/com/example/aiproject1/
│   ├── Main.java                          # Launch screen with UI styling
│   ├── GeneticAlgorithmApp.java           # Main application controller
│   ├── GeneticAlgorithm.java              # Core GA implementation
│   ├── Chromosome.java                    # Individual solution representation
│   └── StatisticsWindow.java              # Performance tracking window
├── src/main/resources/                    # UI assets (background images)
├── pom.xml                                # Maven configuration
├── convergence_data.csv                   # Algorithm performance data
└── report.pdf                             # Detailed project report
```

## 🔧 Prerequisites

- **Java 22** or higher
- **Maven 3.9+**
- **JavaFX SDK 22**

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/WasFia3/Breaking-a-Password-Using-Genetic-Algorithm.git
cd Breaking-a-Password-Using-Genetic-Algorithm/AI-Project1
```

### Build the Project

Using Maven:

```bash
mvn clean install
```

### Run the Application

```bash
mvn javafx:run
```

Or compile and run directly:

```bash
mvn clean compile
mvn javafx:run
```

## 💡 How It Works

### Genetic Algorithm Concepts

1. **Population** - A collection of candidate solutions (chromosomes)
2. **Chromosome** - A 32-bit binary string representing a candidate passcode
3. **Fitness** - Number of bits that match the target passcode (0-32)
4. **Selection** - Tournament selection: randomly pick 5 individuals, select the best
5. **Crossover** - Combine two parent chromosomes at a random midpoint
6. **Mutation** - Randomly flip bits with a given probability

### Algorithm Flow

```
1. Generate random target passcode (32 bits)
2. Initialize random population
3. For each generation (max 200):
   a. Calculate fitness for each individual
   b. If any individual has fitness = 32 → SUCCESS!
   c. Create next generation via:
      - Tournament selection
      - Crossover
      - Mutation
4. Output results and statistics
```

### Key Parameters

- **Population Size** - Number of candidate solutions (default: 100+)
- **Mutation Rate** - Probability of flipping each bit (default: 0.01)
- **Generation Limit** - Maximum generations to evolve (default: 200)

## 🏗️ Core Classes

### `Chromosome.java`
Represents a single candidate solution:
- `genes` - 32-bit binary array
- `fitness` - Fitness score
- `calculateFitness()` - Compare with target
- `mutate()` - Flip bits randomly
- `crossover()` - Combine with another chromosome

### `GeneticAlgorithm.java`
Implements the core GA logic:
- `run()` - Main algorithm loop
- `evolve()` - Create next generation
- `tournamentSelection()` - Select fit individuals
- `getBestFitness()` - Get best score this generation

### `GeneticAlgorithmApp.java`
GUI application controller:
- Sets up the simulation interface
- Manages user interactions
- Displays real-time results
- Handles statistics window

### `Main.java`
Splash screen and application launcher:
- Beautiful animated buttons
- Glow effects on hover
- Launch into main application

## 📊 Output & Results

The algorithm tracks:
- **Generation Count** - Current evolution step
- **Best Fitness** - Highest match score in population
- **Convergence Data** - Saved to `convergence_data.csv`
- **Success Rate** - Whether target was found within limit

Typical results show convergence within 50-100 generations depending on parameters.

## 🎓 Educational Value

This project demonstrates:
- Core concepts of genetic algorithms
- How natural selection drives evolution
- Balance between exploration and exploitation
- Java object-oriented design
- JavaFX GUI development
- Maven project management

Perfect for learning about:
- Evolutionary computation
- AI and optimization algorithms
- Java and GUI frameworks
- Algorithm visualization

## 📈 Performance Metrics

The application logs convergence data to track:
- How quickly the population evolves
- Fitness progression per generation
- Parameter impact on convergence speed

Check `convergence_data.csv` and `report.pdf` for detailed analysis.

## 🤝 Technologies Used

- **Language** - Java 22
- **GUI Framework** - JavaFX 22
- **Build Tool** - Maven 3.11.0
- **Testing** - JUnit 5 (Jupiter)
- **IDE Compatible** - IntelliJ IDEA, Eclipse, VS Code

## 📚 Additional Resources

- See `report.pdf` for detailed implementation notes
- Check `convergence_data.csv` for algorithm performance data
- Review source code comments for implementation details


