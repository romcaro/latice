package latice.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
	
	private List<Tile> tiles = new ArrayList<Tile>();
	private static final int maxSize = 5;
	
	public void addTile(Tile tile) {
		if (this.size() >= maxSize) {
			System.out.println("Rack full");
		} else {
		tiles.add(tile);
		}
	}
	
	public void removeTile(Tile tile) {
		tiles.remove(tile);
	}
	
	public boolean isEmpty() {
		return tiles.isEmpty();
	}
	
	public int size() {
		return tiles.size();
	}
	
	public void getRack() {
		for (Tile tile : tiles) {
			System.out.println(tile);
		}
	}

}
