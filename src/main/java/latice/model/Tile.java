package latice.model;

public class Tile {
	private Color color;
	private Shape shape;
	
	public Tile(Color color, Shape shape) {
		super();
		this.color = color;
		this.shape = shape;
	}

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
