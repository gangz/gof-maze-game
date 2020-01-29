package maze;

import java.util.Collection;
import java.util.HashMap;

public class Maze {
	private HashMap<Position,Room> rooms = new HashMap<>();
	private int rows;
	private int cols;
	private Room firstRoom;
	private Room endRoom;
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

	public void setRooms(HashMap<Position, Room> rooms) {
		this.rooms = rooms;
	}

	public void setFirstRoom(Room firstRoom) {
		this.firstRoom = firstRoom;
	}

	public void setEndRoom(Room endRoom) {
		this.endRoom = endRoom;
	}
}
