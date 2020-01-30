package maze.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

public class AlgorithmSelectionPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JPanel container;
	private JRadioButton recursiveBackTrackerButton;
	private JRadioButton randomPrimButton;
	public AlgorithmSelectionPanel() {
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel("迷宫创建算法");
		this.add(label);
		container = new JPanel();
		this.add(container);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		recursiveBackTrackerButton = new JRadioButton("递归回溯");
		recursiveBackTrackerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		container.add(recursiveBackTrackerButton);

		randomPrimButton = new JRadioButton("随机Prim");
		randomPrimButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		container.add(randomPrimButton);
	}
	
	

}
