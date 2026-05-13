package latice.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
	
	private List<Tile> tiles = new ArrayList<Tile>();
	private static final int MAX_SIZE = 5;

	
	public boolean addTile(Tile tile) {
	    if (isFull()) 
	    	return false;
	    return tiles.add(tile);
	}
	
	public boolean removeTile(Tile tile) {
		return tiles.remove(tile);
	}
	
	public boolean isEmpty() {
		return tiles.isEmpty();
	}
	
	public int size() {
		return tiles.size();
	}
	
	public boolean isFull() {
	    return tiles.size() >= MAX_SIZE;
	}
	
	public List<Tile> getRack() {
		return new ArrayList<>(tiles);
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Tile tile : tiles) {
	        sb.append(tile.getShape()).append("-").append(tile.getColor()).append(" | ");
	    }
	    return sb.toString();
	}
	
}
