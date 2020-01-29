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
	private int roomWidth;
	private int wallWidth;
	
	HashMap<Position,MapSite> map = new HashMap<>();

	public MazePanel(int rows, int cols) {
		if (rows%2==0) rows++;
		if (cols%2==0) cols++;
		this.rows = rows;
		this.cols = cols;
		this.roomWidth = 30;
		this.wallWidth = 10;
		this.setKeyListener();
		for (int i=0;i<rows;i++) {
			for (int j =0;j<cols;j++) {
				if (i%2==1 && j%2==1)
					map.put(new Position(i,j),new Room());
				else
					map.put(new Position(i,j),new Wall());
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for ( Entry<Position, MapSite> entry:map.entrySet()) {
			Position pos = entry.getKey();
			MapSite mapSite = entry.getValue();
			g.setColor(getColor(mapSite));
			if (mapSite instanceof Room)
				g.fillRect(getRoomY(pos), getRoomX(pos), roomWidth, roomWidth);
			else
			{
				if (pos.getY()%2==0) {
					g.fillRect(getWallY(pos), getWallXV(pos), wallWidth, roomWidth+wallWidth);
				}else {
					g.fillRect(getWallY(pos), getWallXH(pos), roomWidth+wallWidth,wallWidth);
				}
			}		
		}
	}

	private int getWallXV(Position pos) {
		return (pos.getX()-1)/2*(roomWidth+wallWidth);
	}

	private int getWallXH(Position pos) {
		return pos.getX()/2*(roomWidth+wallWidth);
	}

	private int getWallY(Position pos) {
		return pos.getY()/2*(roomWidth+wallWidth);
	}

	private int getRoomX(Position pos) {
		return getWallXH(pos)+wallWidth;
	}

	private int getRoomY(Position pos) {
		return getWallY(pos)+wallWidth;
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
