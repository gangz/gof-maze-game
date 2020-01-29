package maze.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import maze.MapSite;
import maze.Room;
import maze.Wall;

public class Path {
	
	private Set<Room> rooms = new HashSet<>();
	public Set<Room> getRooms() {
		return rooms;
	}

	public List<Wall> getFrontier() {
		return frontier;
	}

	public Collection<Wall> getWalls() {
		return walls;
	}

	private List<Wall> frontier = new ArrayList<>();
	private Collection<Wall> walls = new HashSet<>();

	public Path() {
	}

	public void addWall(Wall wall) {
		this.walls.add(wall);
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
		putAllWallsInFrontier(room);
	}

	private void putAllWallsInFrontier(Room room) {
		for (MapSite wall:room.getNeighbors()) {
			if (containsRoomsNotInPath((Wall)wall))
				frontier.add((Wall)wall);
		}
	}

	public Wall getRandomFrontier() {
		while(true) {
			Collections.shuffle(frontier);
			Wall wall = frontier.get(0);
			if (containsRoomsNotInPath(wall)) {
				frontier.remove(wall);
				return wall;
			}
		}
	}

	private boolean containsRoomsNotInPath(Wall wall) {
		for (MapSite room:wall.getNeighbors()) {
			if (!rooms.contains(room)) return true;
		}
		return false;
	}

	public Room getFrontierRoom(Wall wall) {
		for (MapSite room:wall.getNeighbors()) {
			if (!rooms.contains(room)) {
				return (Room)room;
			}
		}
		return null;
	}
}
