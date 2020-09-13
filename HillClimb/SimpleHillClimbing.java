package HillClimb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleHillClimbing {

	private final List<Double> universe;
	private final Problem p;
	private ArrayList<Double> bestSolution;

	public SimpleHillClimbing(final List<Double> universe, Problem p) {
		this.universe = universe;
		this.p = p;
	}

	public double findOptima() {
		boolean shouldContinue;
		
		ArrayList<Double> localBestSolution = new ArrayList<>();
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			localBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - localBestSolution.getMinVal-ues().get(dim)));
		}

		
		do {
			// Select a random neighbour
			double newSolution = this.universe.get(this.getRandomIndex());
			// If a new solution's value is greater than current, best solution
			if (bestSolution < newSolution) {
				// Change the best solution
				bestSolution = newSolution;
				// And continue searching
				shouldContinue = true;
			} else {
				// Otherwise stop
				shouldContinue = false;
			}
		} while (shouldContinue);

		return bestSolution;
	}

	public static void main(String[] args) {

		ArrayList<Double> list = new ArrayList<>();

		// TODO add to list

		SimpleHillClimbing test = new SimpleHillClimbing(list);
		System.out.println(test.findOptima());
	}

}
