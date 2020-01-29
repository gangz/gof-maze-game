package maze;

public class Player {
	private Room currentRoom;
	public Player(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

}
