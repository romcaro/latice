package latice.model;

public class Square {
	
	private Position position;
    private Tile tile;


    public Square(Position position, Tile tile) {
        this.position = position;
        this.tile = null;
    }

    public Position getPosition() {
        return position;
    }
    
    public Tile getTile() {
    	return tile;
    }
    
    public void setTile(Tile tile) { 
    	this.tile = tile;
	}
    
    public boolean isOccupied() { 
    	return tile != null;
    }

	@Override
	public String toString() {
		return "[" + tile.getShape() + "-" + tile.getColor() + "]";
    }
    
    
}
