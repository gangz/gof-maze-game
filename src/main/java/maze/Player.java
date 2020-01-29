package maze;

public class Player {
	private Room currentRoom;
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void move(Direction direction) {
		MapSite next = currentRoom.getNeighbor(direction);
		if (next instanceof Room)
			currentRoom = (Room)next;
	}

}
