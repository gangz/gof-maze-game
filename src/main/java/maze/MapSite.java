package maze;

public abstract class MapSite {
	private Position position;

	public MapSite(Position position) {
		this.position =position;
	}

	abstract void enter();

	public Position getPosition() {
		return position;
	}
}
