import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import utils.Pair;


public class Main {

	public static void main(String[] args) {
	
		int MAPSIZE = 3;
		
		Tile[][] map = new Tile[MAPSIZE][MAPSIZE]; //Map that will contain all tiles
	
		// Queue used for traversing the map in a breadth first fashion
		Queue<Pair<Integer, Integer>> BFTqueue = new ArrayDeque<>();
		
		// Initial values used for selecting a value in the first row as the initial tile
		int initial_j = new Random().nextInt(MAPSIZE);
		int initial_i = 0; //THe first row is ALWAYS at index 0 (obviously)
		
		System.out.println("Initial i: " + initial_i);
		System.out.println("Initial j: " + initial_j);
		System.out.println();
		
		map[initial_i][initial_j] = new Tile(initial_j, MAPSIZE);
		
		/*
		 * Whilst the queue is not empty, pop first element, see if it can be
		 * populated. If it can, populate it and add its children.
		 * Otherwise, just ignore it and continue
		 */
		BFTqueue.add(new Pair<Integer, Integer>(initial_i, initial_j - 1));
		BFTqueue.add(new Pair<Integer, Integer>(initial_i, initial_j + 1));
		BFTqueue.add(new Pair<Integer, Integer>(initial_i + 1, initial_j));
		
		while (!BFTqueue.isEmpty()) {
			Pair<Integer, Integer> coordinates = BFTqueue.poll();
			int newi = coordinates.getLeft();
			int newj = coordinates.getRight();
			
			//Checks if it can be populated and also if there is an existing tile
			if (canBePopulated(newi, newj, MAPSIZE) && map[newi][newj] == null) {
				
				map[newi][newj] = new Tile(null, getEdges(newi, newj, MAPSIZE));
				//Add all children nodes to the list
				BFTqueue.add(new Pair<Integer, Integer>(newi, newj - 1));
				BFTqueue.add(new Pair<Integer, Integer>(newi, newj + 1));
				BFTqueue.add(new Pair<Integer, Integer>(newi + 1, newj));
			}
			//Else, nothing happens
		}
		
		//Representation of the finalized map
		for (int i = 0; i < MAPSIZE; i++) {
			for (int j = 0; j < MAPSIZE; j++) {
				System.out.print(map[i][j].toString() + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * Bound checking to see if map tile can be populated. DOES NOT check
	 * if it is ALREADY populated
	 */
	public static boolean canBePopulated(int newi, int newj, int mapSize) {
		return (newi >= 0) && (newj >= 0) && (newi < mapSize) && (newj < mapSize);
	}
	
	public static Direction[] getEdges(int newi, int newj, int mapSize) {
		ArrayList<Direction> edges = new ArrayList<Direction>();
		
		if (newj == 0 ) 
			edges.add(Direction.WEST);
		if (newj == mapSize - 1)
			edges.add(Direction.EAST);
		
		if (newi == 0 ) 
			edges.add(Direction.NORTH);
		if (newi == mapSize - 1)
			edges.add(Direction.SOUTH);
		return edges.toArray(new Direction[edges.size()]);
		
	}

}
