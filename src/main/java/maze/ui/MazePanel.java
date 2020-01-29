package maze.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Map.Entry;
import maze.MapSite;
import maze.Maze;
import maze.Position;
import maze.Room;
import maze.Wall;

public class MazePanel  extends JPanel {
	private static final long serialVersionUID = 1L;
	private int roomWidth;
	private int wallWidth;
	private Maze maze;
	

	public MazePanel(Maze maze) {
		this.roomWidth = 30;
		this.wallWidth = 10;
		this.maze = maze;
		this.setKeyListener();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for ( Entry<Position, MapSite> element:maze.elements()) {
			Position pos = element.getKey();
			MapSite mapSite = element.getValue();
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
