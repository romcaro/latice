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
		
		
		Pool PlayerOnePool = pool.splitIntoTwoPools()[0];
		List<Tile> PlayerOneTiles;
		PlayerOneTiles = PlayerOnePool.shuffle();
		for (Tile tile : PlayerOneTiles) {
			System.out.println(tile.getShape()+ " " + tile.getColor());
		}
		
		 Pool PlayerTwoPool = pool.splitIntoTwoPools()[1];
		 List<Tile> PlayerTwoTiles;
		 PlayerTwoTiles = PlayerTwoPool.shuffle();
		 for (Tile tile : PlayerTwoTiles) {
				System.out.println(tile.getShape()+ " " + tile.getColor());
			}
		 
		 System.out.println("Player One Pool size: " + PlayerOnePool.size());
		 System.out.println("Player Two Pool size: " + PlayerOnePool.size());
	}
}
