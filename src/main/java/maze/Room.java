package maze;

public class Room extends MapSite {
	private Position position;

	public Room(Position position) {
		this.position = position;
	}

	@Override
	void enter() {

	}

	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + position;
	}

}
