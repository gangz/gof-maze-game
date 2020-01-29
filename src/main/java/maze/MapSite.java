package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class MapSite {
	private Map<Direction, MapSite> neighbors;
	public MapSite() {
		neighbors = new HashMap<>();
	}

	abstract void enter();

	
	public void addNeighbor(Direction direction, MapSite site) {
		neighbors.put(direction, site);
	}
	
	public MapSite getNeighbor(Direction direction) {
		return neighbors.get(direction);
	}
	
	public int neighborsCount() {
		return neighbors.size();
	}
	
	public List<MapSite> getNeighbors() {
		return new ArrayList<>(neighbors.values());
	}

	public void removeNeighbor(MapSite neighbor) {
		for (Entry<Direction, MapSite> entry:neighbors.entrySet()) {
			if(entry.getValue().equals(neighbor)) {
				neighbors.remove(entry.getKey());
				break;
			}
		}
	}
	
	public Map<Direction, MapSite> getNeighborEntries() {
		return neighbors;
	}

}
