package latice.model;

public class Square {
	
	private Position position;
    private Tile tile;
    private SquareType type = SquareType.NORMAL;

    public Square(Position position) {
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
	
	public void setType(SquareType type) {
	    this.type = type;
	}
	
	public SquareType getType() {
	    return type;
	}
	
	@Override
	public String toString() {
	    if (isOccupied()) {
	        return "[" + tile.getShape() + "-" + tile.getColor() + "]";
	    }
	    return switch (type) {
	        case SUN  -> "[SUN]";
	        case MOON -> "[MON]";
	        default   -> "[ - ]";
	    };
	}
}

