import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tile {
	
	private static PathAssigner pathAssigner = new PathAssigner();
	private Path path;

	public Tile(Direction dir, Direction[] edges) {
		this.path = pathAssigner.assignPath(dir, edges);
		
	}
	
	/*
	 * Only used for initial Tile.
	 */
	public Tile(int inital_j, int mapsize) {
        //TODO: fix extraedges
		List<Direction> extraEdges = new ArrayList<>();
		extraEdges.add(Direction.NORTH);
		if (inital_j == 0) 
			extraEdges.add(Direction.WEST);
		if (inital_j == mapsize - 1)
			extraEdges.add(Direction.EAST);
		
		this.path = pathAssigner.assignPath(Direction.SOUTH, 
				                            extraEdges.toArray(new Direction[extraEdges.size()]));
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	//Debugging purposes
	public String toString() {
		return path.toString();
	}
}

class PathAssigner {

	private static List<Path> invalidPathsFromNorthEdge = 
			new ArrayList<>(Arrays.asList(Path.CROSS, Path.WESTT, Path.EASTT, Path.NORTHT,
					                      Path.VERTICAL, Path.WNANGLE, Path.ENANGLE)); 

	private static List<Path> invalidPathsFromSouthEdge = 
			new ArrayList<>(Arrays.asList(Path.CROSS, Path.WESTT, Path.EASTT, Path.SOUTHT,
					                      Path.VERTICAL, Path.WSANGLE, Path.ESANGLE));

	private static List<Path> invalidPathsFromWestEdge = 
		    new ArrayList<>(Arrays.asList(Path.CROSS, Path.WESTT,Path.NORTHT, Path.SOUTHT,
		    		                       Path.HORIZONTAL, Path.WSANGLE, Path.WNANGLE ));

	private static List<Path> invalidPathsFromEastEdge = 
			new ArrayList<>(Arrays.asList(Path.CROSS, Path.EASTT, Path.NORTHT, Path.SOUTHT,
		                                  Path.HORIZONTAL, Path.ESANGLE, Path.ENANGLE));

	/*
	 * Will return a Path for a Tile given an entrance to the tile and which
	 * outer edges it collides with
	 */
	public Path assignPath(Direction dir, Direction[] edges) {
		Path[] possiblePaths = getPossiblePathsFromDirAndEdges(dir, edges);
		int index = new Random().nextInt(possiblePaths.length);
		return possiblePaths[index];
	}

	/*
	 * Returns a list of possible paths given the entrance to the tile and outer
	 * edges
	 */
	private Path[] getPossiblePathsFromDirAndEdges(Direction dir,
			Direction[] edges) {
        List<Path> allPaths = new ArrayList<Path>(Arrays.asList(Path.values()));
        for (Direction edgeDirection: edges) {
        	switch (edgeDirection) {
			case EAST:
				allPaths.removeAll(invalidPathsFromEastEdge);
				break;
			case NORTH:
				allPaths.removeAll(invalidPathsFromNorthEdge);
				break;
			case SOUTH:
				allPaths.removeAll(invalidPathsFromSouthEdge);
				break;
			case WEST:
				allPaths.removeAll(invalidPathsFromWestEdge);
				break;
			default:
				break;
        	
        	}
        }
		return allPaths.toArray(new Path[allPaths.size()]);
	}
}
