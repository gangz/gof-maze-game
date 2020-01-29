package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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



	public void removeNeighbor(MapSite neighbor) {
		for (Entry<Direction, MapSite> entry : neighbors.entrySet()) {
			if (entry.getValue().equals(neighbor)) {
				neighbors.remove(entry.getKey());
				break;
			}
		}
	}

	public List<MapSite> getNeighbors() {
		return new ArrayList<>(neighbors.values());
	}
	
	public Set<Entry<Direction, MapSite>> getNeighborEntries() {
		return neighbors.entrySet();
	}

	public void replaceNeighbor(MapSite from, MapSite to) {
		for (Entry<Direction, MapSite> entry : neighbors.entrySet()) {
			if (entry.getValue().equals(from)) {
				neighbors.put(entry.getKey(), to);
				break;
			}
		}		
	}

}
