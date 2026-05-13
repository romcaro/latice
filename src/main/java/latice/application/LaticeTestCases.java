package latice.application;

import latice.model.GameBoard;
import latice.model.Pool;
import latice.model.Rack;

public class LaticeTestCases {
	
	public static void main(String[] args) {
		
		Pool pool = new Pool();
		pool.generatePool();
		pool.shuffle();

		
		Pool[] mainPool = pool.splitIntoTwoPools();
		Pool playerOnePool = mainPool[0];
		Pool playerTwoPool = mainPool[1];
				 
		System.out.println("\nPlayer One Pool size : " + playerOnePool.size() + " | Player Two Pool size : " + playerTwoPool.size());
		 
		Rack rackPlayerOne = new Rack();
		Rack rackPlayerTwo = new Rack();
		 
		playerOnePool.fillRack(rackPlayerOne);
		playerTwoPool.fillRack(rackPlayerTwo);
		
		System.out.println("\n-------------  Player 1  -------------\n");
		System.out.println(rackPlayerOne);
		System.out.println("\n-------------  Player 2  -------------\n");
		System.out.println(rackPlayerTwo);
		System.out.println();
		
		GameBoard gameboard = new GameBoard(9,9);
		gameboard.initSpecialSquares();
	    System.out.println("\n-------------  Plateau  -------------\n");
	    System.out.println(gameboard);
		
	}
}
