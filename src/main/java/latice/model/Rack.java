package latice.model;

import java.util.List;

public class Rack {
	
	private List<Tile> tiles;
	private int maxSize = 5;
	
	public void addTile(Tile tile) {
		if (this.size() > maxSize) {
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

}
