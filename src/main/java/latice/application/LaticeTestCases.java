package latice.application;

import java.util.ArrayList;
import java.util.List;

import latice.model.Pool;
import latice.model.Tile;

public class LaticeTestCases {
	
	public static void main(String[] args) {
		
		List<Tile> tiles = new ArrayList<Tile>();
		
		Pool pool = new Pool(tiles);
		Pool EmptyPool;
		List<Tile> FilledPool;
		pool.generatePool();
		FilledPool = pool.shuffle();
		
		for (Tile tile : FilledPool) {
			System.out.println(tile.getShape()+ " " + tile.getColor());
		}
		
	}
}
