package maze;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MazeBuilder {
	Maze maze;
	private int rows;
	private int cols;
	HashMap<Position, Room> rooms;
	private Room firstRoom;
	private Room endRoom;
	public MazeBuilder(int rows, int cols) {
		this.maze = new Maze(rows, cols);
		this.rows = rows;
		this.cols = cols;
	}
	
	
	/**
	 * Use Randomized Prim's algorithm to create maze
	 */
	public Maze buildMaze() {
		buildRoomAndWalls();
		breakWalls();
		return maze;
	}


	private void breakWalls() {
		Room room = rooms.get(new Position(new Random().nextInt(rows),new Random().nextInt(cols)));
		Path p = new Path();
		p.addRoom(room);
		while(true) {
			Wall wall = p.getRandomFrontier();
			Room r = p.getFrontierRoom(wall);
			p.addRoom(r);
			p.addWall(wall);
			if (p.frontier.size()==0) break;
			if (p.rooms.size()==rows*cols) {
				System.out.println(p.frontier.size());
				break;
			}
		}
		
		for (Wall wall:p.walls) {
			List<MapSite> rooms = wall.getNeighbors();
			MapSite room_0 = rooms.get(0);
			MapSite room_1 = rooms.get(1);
			room_0.replaceNeighbor(wall,room_1);
			room_1.replaceNeighbor(wall,room_0);
			//			for (MapSite r:wall.getNeighbors()) {
//				((Room)r).removeNeighbor(wall);
//			}
		}
	}

	private void buildRoomAndWalls() {
		rooms = new HashMap<>();
		maze.setRooms(rooms);
		for (int i=0;i<rows;i++) {
			for (int j =0;j<cols;j++) {
				Room room = new Room(new Position(i,j));
				addWalls(room);
				rooms.put(new Position(i,j),room);
			}
		}
		openEntry();
		openExit();
	}

	private void openExit() {
		MapSite wall;
		endRoom = (Room) (rooms.get(new Position(rows-1,cols-1)));
		wall = endRoom.getNeighbor(Direction.RIGHT);
		wall.removeNeighbor(endRoom);
		endRoom.removeNeighbor(wall);
	}

	private void openEntry() {
		firstRoom = (Room) (rooms.get(new Position(0,0)));
		MapSite wall = firstRoom.getNeighbor(Direction.LEFT);
		wall.removeNeighbor(firstRoom);
		firstRoom.removeNeighbor(wall);
	}

	private void addWalls(Room room) {
		Position position = room.getPosition();
		Wall wall = new Wall();
		room.addNeighbor(Direction.RIGHT,wall);
		wall.addNeighbor(Direction.LEFT, room);

		wall = new Wall();
		room.addNeighbor(Direction.DOWN,wall);
		wall.addNeighbor(Direction.UP, room);

		if (position.getX()!=0) {
			wall = (Wall) rooms.get(new Position(position.getX()-1,position.getY())).getNeighbor(Direction.DOWN);
		}else {
			wall = new Wall();
		}
		room.addNeighbor(Direction.UP,wall);
		wall.addNeighbor(Direction.DOWN, room);
		
		if (position.getY()!=0) {
			wall = (Wall) rooms.get(new Position(position.getX(),position.getY()-1)).getNeighbor(Direction.RIGHT);
		}else {
			wall = new Wall();
		}
		room.addNeighbor(Direction.LEFT, wall);
		wall.addNeighbor(Direction.RIGHT, room);
	}
}
