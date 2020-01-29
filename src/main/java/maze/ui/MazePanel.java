package maze.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.HashMap;
import java.util.Map.Entry;
import maze.MapSite;
import maze.Position;
import maze.Room;
import maze.Wall;

public class MazePanel  extends JPanel {
	private static final long serialVersionUID = 1L;
	private int rows;
	private int cols;
	private int latticeWidth;
	HashMap<Position,MapSite> map = new HashMap<>();

	public MazePanel(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.latticeWidth = 15;
		this.setKeyListener();
		map.put(new Position(1,1),new Room());
		map.put(new Position(1,2),new Wall());
		map.put(new Position(1,3),new Wall());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for ( Entry<Position, MapSite> entry:map.entrySet()) {
			Position pos = entry.getKey();
			MapSite mapSite = entry.getValue();
			g.setColor(getColor(mapSite));
			g.fillRect(pos.getY()*latticeWidth, pos.getX()*latticeWidth, latticeWidth, latticeWidth);
		}
	}
	
	private Color getColor(MapSite mapSite) {
		if (mapSite instanceof Room) {
			return Color.WHITE;
		}
		if (mapSite instanceof Wall) {
			return Color.BLACK;
		}
		return Color.GRAY;
	}

	private void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				repaint();
				}
		});
	}
}
