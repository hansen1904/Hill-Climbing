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
		
		//Init Local and Global
		ArrayList<Double> localBestSolution = new ArrayList<>();
		ArrayList<Double> globalBestSolution = new ArrayList<>();
		
		//Picking Random for Local
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			localBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}
		
		//Picking Random for Global
		for(int dim = 0; dim < p.getDimensions(); dim++) {
			globalBestSolution.add(p.getMinValues().get(dim) + Math.random() * (p.getMaxValues().get(dim) - p.getMinValues().get(dim)));
		}
		
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
				
				//If first neighbour set best to that.
				if(n == 0) {
					bestNeighbours = ArrNeighbours;
				}
				
				//Checking if the neighbour is better than bestNeighbour 
				if(p.Eval(ArrNeighbours) > p.Eval(bestNeighbours)) {
					bestNeighbours = ArrNeighbours;
				}
			}
			
			//After looping all Neighbour, we compare localBest to the Best neighbour we got.
			if (p.Eval(localBestSolution) < p.Eval(bestNeighbours)) {
				localBestSolution = bestNeighbours;
			}
			
			//Checking if we have run though our iterations.
			if(count < iterations) {
				count++;
			} else {
				shouldContinue = false;
			}
			
		} while (shouldContinue);
	
		
		//Checking if Local is better than Global.
		if (p.Eval(localBestSolution) > p.Eval(globalBestSolution)) {
			globalBestSolution = localBestSolution;
		}
		
		//Returning the best solution.
		return globalBestSolution;
	}

	public static void main(String[] args) {

		//Difference types of problems.
		P1 p = new P1();
//		P2 p = new P2();
//		RevAckley p = new RevAckley();

		//Adding Data
		ArrayList<Double> list = new ArrayList<>();
		
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
		
		//Running our Hill Climbing Program.
		SimpleHillClimbing test = new SimpleHillClimbing(list, p);
		System.out.println(p.Eval(test.findOptima(10, 0.5, 10)));
	}

}
