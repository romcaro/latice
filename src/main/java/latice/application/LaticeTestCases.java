package latice.application;

import java.util.ArrayList;
import java.util.List;

import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;

public class LaticeTestCases {
	
	public static void main(String[] args) {
		
		List<Tile> tiles = new ArrayList<Tile>();
		
		Pool pool = new Pool(tiles);
		List<Tile> FilledPool;
		pool.generatePool();
		FilledPool = pool.shuffle();
		
		for (Tile tile : FilledPool) {
			System.out.println(tile.getShape()+ " " + tile.getColor());
		}
		
		
		Pool[] mainPool = pool.splitIntoTwoPools();
		Pool playerOnePool = mainPool[0];
		Pool playerTwoPool = mainPool[1];
				 
		System.out.println("\nDone. Player One Pool size : " + playerOnePool.size() + " | Player Two Pool size : " + playerTwoPool.size());
		 
		Rack rackPlayerOne = new Rack();
		Rack rackPlayerTwo = new Rack();
		 
		rackPlayerOne.fillRack(playerOnePool);
		rackPlayerTwo.fillRack(playerTwoPool);
		
		System.out.println("\n-------------  Player 1  -------------\n");
		rackPlayerOne.getRack();
		System.out.println("\n-------------  Player 2  -------------\n");
		rackPlayerTwo.getRack();
	}
}
