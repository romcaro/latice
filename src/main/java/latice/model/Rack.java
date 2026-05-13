package latice.model;

import java.util.ArrayList;
import java.util.List;

public class Rack {
	
	private List<Tile> tiles = new ArrayList<Tile>();
	
	public boolean addTile(Tile tile) {
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
	
	public List<Tile> getRack() {
		return tiles;
	}

	@Override
	public String toString() {
		return "" + ((Tile) this.getRack()).getColor();
	}
	
}
