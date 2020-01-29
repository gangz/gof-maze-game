package maze;

import java.util.Collection;
import java.util.HashMap;

public class Maze {
	private HashMap<Position,Room> rooms = new HashMap<>();
	private int rows;
	private int cols;
	private Room firstRoom;
	private Room endRoom;
	private Player player;
	public Maze(int rows, int cols) {
		this.setRows(rows);
		this.setCols(cols);
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public Collection<Room> rooms() {
		return rooms.values();
	}

	public Room getStartRoom() {
		return firstRoom;
	}
	
	public Room getEndRoom() {
		return endRoom;
	}

	public void build(HashMap<Position, Room> rooms, Room firstRoom, Room endRoom) {
		this.rooms = rooms;
		this.firstRoom = firstRoom;
		this.endRoom = endRoom;
		this.player = new Player(firstRoom);
		
	}

	public Player getPlayer() {
		return this.player;
	}
}
