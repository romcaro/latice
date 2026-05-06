package latice.model;

import java.util.Collections;
import java.util.List;

public class Pool {
	
	private List<Tile> tiles;
	
	
	public Pool(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public List<Tile> generatePool() {
		
	}
	
	public void shuffle() {
		Collections.shuffle(tiles);
	}
	
	public Tile draw() {
		return tiles.getFirst();
	}
	
	public int size() {
		return tiles.size();
	}
	
	public boolean isEmpty() {
		return tiles.size() == 0;
	}
	
	
}