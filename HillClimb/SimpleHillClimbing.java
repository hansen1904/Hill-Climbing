package HillClimb;

import java.util.ArrayList;
import java.util.List;

public class SimpleHillClimbing {

	private final List<Double> universe;
	private final Problem p;

	public SimpleHillClimbing(final List<Double> universe, Problem p) {
		this.universe = universe;
		this.p = p;
	}

	public ArrayList<Double> findOptima(int iterations, double stepSize) {
		boolean shouldContinue = true;
		
		ArrayList<Double> localBestSolution = new ArrayList<>();
		ArrayList<Double> globalBestSolution = new ArrayList<>();
		
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			localBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}
		
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			globalBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}

		int count = 1;
		do {
			
			if(count < iterations) {
				count++;
			} else {
				shouldContinue = false;
			}
			
			//Getting a neighbour
			ArrayList<Double> param = new ArrayList<>();
			for(int dim = 0; dim < p.getDimensions(); dim++) {
				
				param.add(localBestSolution.get(dim) + Math.random() * stepSize);
				if(param.get(dim) < p.getMinValues().get(dim)) {
					param.set(dim, p.getMinValues().get(dim));
				}
				if(param.get(dim) > p.getMaxValues().get(dim)) {
					param.set(dim, p.getMaxValues().get(dim));
				}
			}			

			if (p.Eval(localBestSolution) < p.Eval(param)) {
				localBestSolution = param;
			}
			
		} while (shouldContinue);
		
		//Checking if local is better than Global.
		if (p.Eval(localBestSolution) > p.Eval(globalBestSolution)) {
			globalBestSolution = localBestSolution;
		}
		
		System.out.println("Local: " + localBestSolution);
		System.out.println("Eval Local: " + p.Eval(localBestSolution));
		System.out.println("Global: " + globalBestSolution);
		System.out.println("Eval Global: " + p.Eval(globalBestSolution));
		
		//Returning the best solution.
		return globalBestSolution;
	}

	public static void main(String[] args) {

		ArrayList<Double> list = new ArrayList<>();
		P1 p = new P1();

		list.add(1.0);
		list.add(1.0);
		
		list.add(2.0);
		list.add(2.0);
		
		list.add(3.0);
		list.add(3.0);
		
		list.add(1.0);
		list.add(1.0);
		
		list.add(5.0);
		list.add(5.0);

		SimpleHillClimbing test = new SimpleHillClimbing(list, p);
		System.out.println(test.findOptima(50, 0.5));
	}

}
