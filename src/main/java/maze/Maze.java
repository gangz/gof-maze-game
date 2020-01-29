package maze;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Maze {
	HashMap<Position,Room> rooms = new HashMap<>();
	private int rows;
	private int cols;
	private Room start;
	private Room end;
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
	
	public void buildMaze() {
		buildRoomAndWalls();
		breakWalls();
	}

	/**
	 * Use Randomized Prim's algorithm to create maze
	 */
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
			for (MapSite r:wall.getNeighbors()) {
				((Room)r).removeNeighbor(wall);
			}
		}
	}

	private void buildRoomAndWalls() {
		for (int i=0;i<getRows();i++) {
			for (int j =0;j<getCols();j++) {
				Room room = new Room(new Position(i,j));
				addWalls(room);
				rooms.put(new Position(i,j),room);
			}
		}
		
		start = (Room) (rooms.get(new Position(0,0)));
		end = (Room) (rooms.get(new Position(rows-1,cols-1)));
	}

	private void addWalls(Room room) {
		Position position = room.getPosition();
		if (position.getY()!=getRows()-1) {
			Wall wall = new Wall();
			room.addNeighbor(Direction.RIGHT,wall);
			wall.addNeighbor(Direction.LEFT, room);
		}
		if (position.getX()!=getCols()-1) {
			Wall wall = new Wall();
			room.addNeighbor(Direction.DOWN,wall);
			wall.addNeighbor(Direction.UP, room);

		}
		if (position.getX()!=0) {
			MapSite wall = rooms.get(new Position(position.getX()-1,position.getY())).getNeighbor(Direction.DOWN);
			room.addNeighbor(Direction.UP,wall);
			wall.addNeighbor(Direction.DOWN, room);
		}
		if (position.getY()!=0) {
			MapSite wall = rooms.get(new Position(position.getX(),position.getY()-1)).getNeighbor(Direction.RIGHT);
			room.addNeighbor(Direction.LEFT, wall);
			wall.addNeighbor(Direction.RIGHT, room);
		}
	}

	public Collection<Room> rooms() {
		return rooms.values();
	}

	public Room getStartRoom() {
		return start;
	}
	
	public Room getEndRoom() {
		return end;
	}
}
