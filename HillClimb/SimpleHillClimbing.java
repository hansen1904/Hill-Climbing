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
		boolean shouldContinue;
		
		ArrayList<Double> localBestSolution = new ArrayList<>();
		ArrayList<Double> globalBestSolution = new ArrayList<>();
		
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			localBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}
		
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			globalBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}

		
		do {
			for(int i = 0; i < iterations; i++) {
				
				//Getting a neighbour
				ArrayList<Double> param = new ArrayList<>();
				for(int dim = 0; dim < p.getDimensions(); dim++) {
					
					param.add(p.getMinValues().get(dim) + Math.random() * stepSize + (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
					if(param.get(dim) < p.getMinValues().get(dim)) {
						
					}
				}			
				
			}
			
			//Checking if local is better than Global.
			if (p.Eval(localBestSolution) > p.Eval(globalBestSolution)) {

				globalBestSolution = localBestSolution;
				shouldContinue = true;
				
			} else {
				shouldContinue = false;
			}
			
		} while (shouldContinue);
		
		//Returning the best solution.
		return globalBestSolution;
	}

	public static void main(String[] args) {

		ArrayList<Double> list = new ArrayList<>();
		P1 p = new P1();

		list.add(2.0);
		list.add(0.04);
		list.add(8.4);
		list.add(9.3);
		list.add(4.1);
		list.add(0.00005);
		list.add(77.0);
		list.add(11.0);
		list.add(103.0);
		list.add(99.0);

		SimpleHillClimbing test = new SimpleHillClimbing(list, p);
		System.out.println(test.findOptima(5, 0.5));
	}

}
