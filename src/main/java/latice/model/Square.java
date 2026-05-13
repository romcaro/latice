package latice.model;

public class Square {
	
	private Position position;
    private Tile tile;
    private boolean isSunSquare = false;
    private boolean isMoonSquare = false;
    


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

	public void setSunSquare() {
		isSunSquare = true;
	}
	public void setMoonSquare() {
		isMoonSquare = true;
	}

	@Override
	//pas ouf faut modifier 
	public String toString() {
		if (tile == null) {
			return "[ - ]";
		} else if (isSunSquare) {
			return "[SUN]";
		} else if (isMoonSquare) {
			return "[MON]";
		} else {
			return "[" + tile.getShape() + "-" + tile.getColor() + "]";
		}
	}
}

