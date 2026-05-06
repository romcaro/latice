package latice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pool {
	
	private List<Tile> tiles = new ArrayList<Tile>();
	
	
	public Pool(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public List<Tile> generatePool() {
		for (Shape shape : Shape.values()) {
			for (Color color : Color.values()) {
				tiles.add(new Tile(color, shape));
			} 
		}
		tiles.addAll(tiles);
		return tiles;
	}
	
	public List<Tile> shuffle() {
		Collections.shuffle(tiles);
		return tiles;
	}
	
	public Tile draw() {
		return tiles.removeFirst();
	}
	
	public int size() {
		return tiles.size();
	}
	
	public boolean isEmpty() {
		return tiles.isEmpty();
	}		
	
	public Pool[] splitIntoTwoPools() {
		int halfSize = tiles.size() / 2;
		List<Tile> poolPlayer1 = new ArrayList<>(tiles.subList(0, halfSize));
		List<Tile> poolPlayer2 = new ArrayList<>(tiles.subList(halfSize, tiles.size()));
		return new Pool[] {new Pool(poolPlayer1), new Pool(poolPlayer2)};
	}
	
}