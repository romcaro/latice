package latice.model;

public class Tile {
	private Color color;
	private Shape shape;
	
	
	public Color getColor() {
		return color;
	}

	public Shape getShape() {
		return shape;
	}
	
	public boolean matchesColor(Tile t) {
		return false;
	}
	
	public boolean matchesShape(Tile t) {
		return false;
	}

}	
