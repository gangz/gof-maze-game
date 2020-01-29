package maze;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MazeBuilder {
	private Maze maze;
	private int rows;
	private int cols;
	private HashMap<Position, Room> rooms;
	private Room firstRoom;
	private Room endRoom;

	public MazeBuilder(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.maze = new Maze(rows, cols);
	}

	/**
	 * Use Randomized Prim's algorithm to create maze
	 */
	public Maze buildMaze() {
		buildRoomAndWalls();
		breakWalls();
		maze.setFirstRoom(firstRoom);
		maze.setEndRoom(endRoom);
		return maze;
	}

	private void buildRoomAndWalls() {
		rooms = new HashMap<>();
		maze.setRooms(rooms);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Room room = new Room(new Position(i, j));
				addWalls(room);
				rooms.put(new Position(i, j), room);
			}
		}
		openEntry();
		openExit();
	}
	
	private void breakWalls() {
		Path path = new Path();
		Room randomRoom = rooms.get(new Position(new Random().nextInt(rows), new Random().nextInt(cols)));
		path.addRoom(randomRoom);
		while (true) {
			Wall wall = path.getRandomFrontier();
			Room r = path.getFrontierRoom(wall);
			path.addRoom(r);
			path.addWall(wall);
			if (path.getRooms().size() == rows * cols) {
				break;
			}
		}

		for (Wall wall : path.getWalls()) {
			List<MapSite> rooms = wall.getNeighbors();
			MapSite room_0 = rooms.get(0);
			MapSite room_1 = rooms.get(1);
			room_0.replaceNeighbor(wall, room_1);
			room_1.replaceNeighbor(wall, room_0);
		}
	}

	private void openExit() {
		MapSite wall;
		endRoom = (Room) (rooms.get(new Position(rows - 1, cols - 1)));
		wall = endRoom.getNeighbor(Direction.RIGHT);
		wall.removeNeighbor(endRoom);
		endRoom.removeNeighbor(wall);
	}

	private void openEntry() {
		firstRoom = (Room) (rooms.get(new Position(0, 0)));
		MapSite wall = firstRoom.getNeighbor(Direction.LEFT);
		wall.removeNeighbor(firstRoom);
		firstRoom.removeNeighbor(wall);
	}

	private void addWalls(Room room) {
		Position position = room.getPosition();
		Wall wall = new Wall();
		room.setNeighbor(Direction.RIGHT, wall);
		wall.setNeighbor(Direction.LEFT, room);

		wall = new Wall();
		room.setNeighbor(Direction.DOWN, wall);
		wall.setNeighbor(Direction.UP, room);

		if (position.getX() != 0) {
			wall = (Wall) rooms.get(new Position(position.getX() - 1, position.getY())).getNeighbor(Direction.DOWN);
		} else {
			wall = new Wall();
		}
		room.setNeighbor(Direction.UP, wall);
		wall.setNeighbor(Direction.DOWN, room);

		if (position.getY() != 0) {
			wall = (Wall) rooms.get(new Position(position.getX(), position.getY() - 1)).getNeighbor(Direction.RIGHT);
		} else {
			wall = new Wall();
		}
		room.setNeighbor(Direction.LEFT, wall);
		wall.setNeighbor(Direction.RIGHT, room);
	}
}
