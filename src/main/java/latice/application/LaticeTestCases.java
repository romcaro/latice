package latice.application;

import java.util.ArrayList;
import java.util.List;

import latice.model.Color;
import latice.model.GameBoard;
import latice.model.Pool;
import latice.model.Position;
import latice.model.Rack;
import latice.model.Shape;
import latice.model.Square;
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
		System.out.println();
		
		GameBoard gameboard = new GameBoard(9,9);
		
		Tile tileee = new Tile(Color.GREEN,Shape.GECKO); 
		Square moon = new Square(new Position(4,4));
		Square sun = new Square(new Position(0,0));
		moon.setMoonSquare();
		sun.setSunSquare();
		gameboard.setSquare(4, 4, moon);
		
		//alone up
		gameboard.setSquare(0,4,sun);
		
		//right alone
		gameboard.setSquare(4,8,sun);
		
		//bottom alone
		gameboard.setSquare(8,4,sun);
		
		//left alone
		gameboard.setSquare(4,0,sun);
		
		
		//up left
		gameboard.setSquare(0,0,sun);
		gameboard.setSquare(1,1,sun);
		gameboard.setSquare(2,2,sun);
		
		//up right
		gameboard.setSquare(0,8,sun);
		gameboard.setSquare(1,7,sun);
		gameboard.setSquare(2,6,sun);
		
		//bottom right
		gameboard.setSquare(8,8,sun);
		gameboard.setSquare(7,7,sun); 
		gameboard.setSquare(6,6,sun);
		
		//bottom left
		gameboard.setSquare(8,0,sun);
		gameboard.setSquare(7,1,sun);
		gameboard.setSquare(6,2,sun);
		

		gameboard.getSquare(4, 4).setTile(tileee);
		
		//Sun
		gameboard.getSquare(0, 0).setTile(tileee);
		
		for (int x=0;x<9;x++) {
			for (int y=0;y<9;y++) {
				System.out.print(gameboard.getSquare(x, y).toString() + " ");
			}
			System.out.println();
		}
		
		
	}
}
