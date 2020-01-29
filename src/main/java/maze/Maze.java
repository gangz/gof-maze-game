package maze;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Maze {
	HashMap<Position,MapSite> map = new HashMap<>();
	private int rows;
	private int cols;
	
	public Maze(int rows, int cols) {
		if (rows%2==0) rows++;
		if (cols%2==0) cols++;
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
	}

	private void buildRoomAndWalls() {
		for (int i=0;i<getRows();i++) {
			for (int j =0;j<getCols();j++) {
				if (i%2==1 && j%2==1)
					map.put(new Position(i,j),new Room());
				else
					map.put(new Position(i,j),new Wall());
			}
		}
	}

	public Set<Entry<Position, MapSite>> elements() {
		return map.entrySet();
	}
}
