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

	public ArrayList<Double> findOptima(int iterations, double stepSize, int neighbours) {
		boolean shouldContinue = true;
		
		ArrayList<Double> localBestSolution = new ArrayList<>();
		ArrayList<Double> globalBestSolution = new ArrayList<>();
		
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			localBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}
		
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			globalBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}
			
		System.out.println("Start Local: " + p.Eval(localBestSolution));
		System.out.println("Start Global: " + p.Eval(globalBestSolution));
		
		int count = 1;
		do {
			
			//Getting a neighbour
			ArrayList<Double> bestNeighbours = new ArrayList<>();
			for(int n = 0; n < neighbours; n++) {
				
				ArrayList<Double> ArrNeighbours = new ArrayList<>();
				
				for(int dim = 0; dim < p.getDimensions(); dim++) {
					
					ArrNeighbours.add(localBestSolution.get(dim) + Math.random() * stepSize);
					if(ArrNeighbours.get(dim) < p.getMinValues().get(dim)) {
						ArrNeighbours.set(dim, p.getMinValues().get(dim));
					}
					if(ArrNeighbours.get(dim) > p.getMaxValues().get(dim)) {
						ArrNeighbours.set(dim, p.getMaxValues().get(dim));
					}
				}
				System.out.println("Neighbour: " + p.Eval(ArrNeighbours) + ", " + ArrNeighbours);
				
				if(n == 0) {
					bestNeighbours = ArrNeighbours;
				}
				
				if(p.Eval(ArrNeighbours) > p.Eval(bestNeighbours)) {
					bestNeighbours = ArrNeighbours;
				}
			}
						
			if (p.Eval(localBestSolution) < p.Eval(bestNeighbours)) {
				localBestSolution = bestNeighbours;
			}
			
			if(count < iterations) {
				count++;
			} else {
				shouldContinue = false;
			}
			
		} while (shouldContinue);
	
		
		System.out.println("Eval Local: " + p.Eval(localBestSolution));
		System.out.println("Eval Global: " + p.Eval(globalBestSolution));
		
		//Checking if local is better than Global.
		if (p.Eval(localBestSolution) > p.Eval(globalBestSolution)) {
			globalBestSolution = localBestSolution;
		}
		
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
		test.findOptima(10, 0.5, 5);
	}

}
