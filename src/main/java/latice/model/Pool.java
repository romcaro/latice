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
		return tiles.size() == 0;
	}
	
	public Pool[] splitIntoTwoPools() {
		int halfSize = tiles.size() / 2;
		List<Tile> pool1 = tiles.subList(0, halfSize);
		List<Tile> pool2 = tiles.subList(halfSize, tiles.size());
		Pool p1 = new Pool(pool1);
		Pool p2 = new Pool(pool2);
		return new Pool[] {p1, p2};
	}
	
}