package maze.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.Maze;
import maze.builder.MazeBuilder;


public class MainFrame extends JFrame { 
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			MainFrame mazeFrame = new MainFrame();
			mazeFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel contentPane;
	private MazePanel mazePanel;
	
	public MainFrame() {
		MazeBuilder mazeBuilder  = new MazeBuilder(16,16);

		setTitle("Maze Example of GoF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		mazePanel = new MazePanel(mazeBuilder.buildMaze());
		contentPane.add(mazePanel);
	}

}
