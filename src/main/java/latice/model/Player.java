package latice.model;

public class Player {
	
	private String name;
	private Pool pool;
	private Rack rack;
	private int score;
	private int tilesPlayed;

	public Player(String name) {
		this.name = name;
		this.rack = new Rack();
		this.score = 0;
		this.tilesPlayed = 0;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int points) {
		this.score += points;
	}

	public Rack getRack() {
		return rack;
	}
	
	public Pool getPool() {
		return pool;
	}
	
	public void setPool(Pool pool) {
		this.pool = pool;
	}
	
	public int getTilesPlayed() {
		return tilesPlayed;
	}
	
	public void addTilesPlayed() {
		this.tilesPlayed++;
	}
	
	@Override
	public String toString() {
		return "Player{name='" + name + "', score=" + score + ", tilesPlayed=" + tilesPlayed + "}";
	}

}
