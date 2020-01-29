package maze.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Map.Entry;

import maze.Direction;
import maze.MapSite;
import maze.Maze;
import maze.Player;
import maze.Position;
import maze.Room;
import maze.Wall;

public class MazePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int roomWidth;
	private int wallWidth;
	private Maze maze;
	private Room start;
	private Room end;

	public MazePanel(Maze maze) {
		this.roomWidth = 40;
		this.wallWidth = 2;
		this.maze = maze;
		this.start = maze.getStartRoom();
		this.end = maze.getEndRoom();
		this.setKeyListener();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Room room : maze.rooms()) {
			drawRoom(g, room);
		}
		drawPlayer(g, maze.getPlayer());
	}

	private void drawPlayer(Graphics g, Player player) {
		Position pos = player.getCurrentRoom().getPosition();
		g.setColor(Color.RED);
		g.fillOval(pos.getY() * roomWidth, pos.getX() * roomWidth, roomWidth, roomWidth);
	}

	private void drawRoom(Graphics g, Room room) {
		Position pos = room.getPosition();
		if (room.equals(this.start) || room.equals(this.end)) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(pos.getY() * roomWidth, pos.getX() * roomWidth, roomWidth, roomWidth);
		for (Entry<Direction, MapSite> neighbor : room.getNeighborEntries()) {
			if (neighbor.getValue() instanceof Wall) {
				Wall wall = (Wall) neighbor.getValue();
				Direction direction = neighbor.getKey();
				int halfWallWidth = wallWidth;
				if (wall.getNeighbors().size() == 2) {
					halfWallWidth = wallWidth / 2;
				}
				if (direction.equals(Direction.UP)) {
					g.setColor(Color.BLACK);
					g.fillRect(pos.getY() * roomWidth, pos.getX() * roomWidth, roomWidth, halfWallWidth);
				}

				if (direction.equals(Direction.DOWN)) {
					g.setColor(Color.BLACK);
					g.fillRect(pos.getY() * roomWidth, (pos.getX() + 1) * roomWidth - halfWallWidth, roomWidth,
							halfWallWidth);
				}

				if (direction.equals(Direction.LEFT)) {
					g.setColor(Color.BLACK);
					g.fillRect(pos.getY() * roomWidth, pos.getX() * roomWidth, halfWallWidth, roomWidth);
				}

				if (direction.equals(Direction.RIGHT)) {
					g.setColor(Color.BLACK);
					g.fillRect((pos.getY() + 1) * roomWidth - halfWallWidth, pos.getX() * roomWidth, halfWallWidth,
							roomWidth);
				}
			}
		}

	}

	private void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					maze.getPlayer().move(Direction.UP);
					break;
				case KeyEvent.VK_DOWN:
					maze.getPlayer().move(Direction.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					maze.getPlayer().move(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					maze.getPlayer().move(Direction.RIGHT);
					break;
				}
				repaint();
				super.keyPressed(e);
			}

		});
	}
}
