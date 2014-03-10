import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends GameVisualiser {
	private JTable xTable;
		/**
		 * @wbp.parser.entryPoint
		 */
		public void run()
		{
			JFrame w = new JFrame();
			w.setDefaultCloseOperation(w.EXIT_ON_CLOSE);
            //w.setDefaultLookAndFeelDecorated(true);
			//all the components
			
			w.pack();
			w.setLocationByPlatform(true);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{124, 0, 0};
			gridBagLayout.rowHeights = new int[] {24, 30};
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 1.0};
			w.getContentPane().setLayout(gridBagLayout);
			
			JPanel mapPanel = new JPanel();
			GridBagConstraints gbc_mapPanel = new GridBagConstraints();
			gbc_mapPanel.anchor = GridBagConstraints.WEST;
			gbc_mapPanel.insets = new Insets(0, 0, 5, 5);
			gbc_mapPanel.gridx = 0;
			gbc_mapPanel.gridy = 0;
			w.getContentPane().add(mapPanel, gbc_mapPanel);
			GridBagLayout gbl_mapPanel = new GridBagLayout();
			gbl_mapPanel.columnWidths = new int[] {1025, 0};
			gbl_mapPanel.rowHeights = new int[] {810};
			gbl_mapPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_mapPanel.rowWeights = new double[]{0.0};
			mapPanel.setLayout(gbl_mapPanel);
			
			JLabel mapLabel = new JLabel("");
			mapLabel.setIcon(new ImageIcon(GUI.class.getResource(mapVisualisable.getMapFilename())));
			mapLabel.setVerticalAlignment(SwingConstants.TOP);
			mapLabel.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_mapLabel = new GridBagConstraints();
			gbc_mapLabel.insets = new Insets(0, 0, 5, 0);
			gbc_mapLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_mapLabel.gridx = 0;
			gbc_mapLabel.gridy = 0;
			mapPanel.add(mapLabel, gbc_mapLabel);
			
			JLabel counterDetective1 = new JLabel("d1");
			GridBagConstraints gbc_counterDetective1 = new GridBagConstraints();
			gbc_counterDetective1.insets = new Insets(0, 0, 5, 0);
			gbc_counterDetective1.gridx = 0;
			gbc_counterDetective1.gridy = 0;
			mapPanel.add(counterDetective1, gbc_counterDetective1);
			
			JLabel counterDetective2 = new JLabel("d2");
			GridBagConstraints gbc_counterDetective2 = new GridBagConstraints();
			gbc_counterDetective2.insets = new Insets(0, 0, 5, 0);
			gbc_counterDetective2.gridx = 0;
			gbc_counterDetective2.gridy = 0;
			mapPanel.add(counterDetective2, gbc_counterDetective2);
			
			JLabel counterDetective3 = new JLabel("d3");
			GridBagConstraints gbc_counterDetective3 = new GridBagConstraints();
			gbc_counterDetective3.insets = new Insets(0, 0, 5, 0);
			gbc_counterDetective3.gridx = 0;
			gbc_counterDetective3.gridy = 0;
			mapPanel.add(counterDetective3, gbc_counterDetective3);
			
			JLabel counterDetective4 = new JLabel("d4");
			GridBagConstraints gbc_counterDetective4 = new GridBagConstraints();
			gbc_counterDetective4.gridx = 0;
			gbc_counterDetective4.gridy = 0;
			mapPanel.add(counterDetective4, gbc_counterDetective4);
			
			JPanel playerPanel = new JPanel();
			GridBagConstraints gbc_playerPanel = new GridBagConstraints();
			gbc_playerPanel.insets = new Insets(0, 0, 5, 0);
			gbc_playerPanel.anchor = GridBagConstraints.EAST;
			gbc_playerPanel.gridx = 1;
			gbc_playerPanel.gridy = 0;
			w.getContentPane().add(playerPanel, gbc_playerPanel);
			GridBagLayout gbl_playerPanel = new GridBagLayout();
			gbl_playerPanel.columnWidths = new int[]{0, 0};
			gbl_playerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_playerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_playerPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			playerPanel.setLayout(gbl_playerPanel);
			
			JPanel mrXPanel = new JPanel();
			GridBagConstraints gbc_mrXPanel = new GridBagConstraints();
			gbc_mrXPanel.anchor = GridBagConstraints.NORTH;
			gbc_mrXPanel.insets = new Insets(0, 0, 5, 0);
			gbc_mrXPanel.fill = GridBagConstraints.HORIZONTAL;
			gbc_mrXPanel.gridx = 0;
			gbc_mrXPanel.gridy = 0;
			
			playerPanel.add(mrXPanel, gbc_mrXPanel);
			GridBagLayout gbl_mrXPanel = new GridBagLayout();
			gbl_mrXPanel.columnWidths = new int[]{60, 60, 60};
			gbl_mrXPanel.rowHeights = new int[] {20, 20, 30, 30, 30};
			gbl_mrXPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_mrXPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
			mrXPanel.setLayout(gbl_mrXPanel);
			
			JLabel xPhoto = new JLabel("");
			xPhoto.setIcon(new ImageIcon(GUI.class.getResource("./Images/mrX.jpg")));
			GridBagConstraints gbc_xPhoto = new GridBagConstraints();
			gbc_xPhoto.gridheight = 2;
			gbc_xPhoto.insets = new Insets(0, 0, 5, 5);
			gbc_xPhoto.gridx = 0;
			gbc_xPhoto.gridy = 0;
			mrXPanel.add(xPhoto, gbc_xPhoto);
			
			JLabel xName = new JLabel("Mr X");
			GridBagConstraints gbc_xName = new GridBagConstraints();
			gbc_xName.gridwidth = 2;
			gbc_xName.insets = new Insets(0, 0, 5, 0);
			gbc_xName.gridx = 1;
			gbc_xName.gridy = 0;
			mrXPanel.add(xName, gbc_xName);
			
			JLabel xHiddenMove = new JLabel("Hidden Move");
			xHiddenMove.setBackground(Color.GRAY);
			GridBagConstraints gbc_xHiddenMove = new GridBagConstraints();
			gbc_xHiddenMove.insets = new Insets(0, 0, 5, 5);
			gbc_xHiddenMove.gridx = 1;
			gbc_xHiddenMove.gridy = 1;
			mrXPanel.add(xHiddenMove, gbc_xHiddenMove);
			
			JLabel xDoubleMove = new JLabel("Double Move");
			xDoubleMove.setBackground(new Color(70, 130, 180));
			GridBagConstraints gbc_xDoubleMove = new GridBagConstraints();
			gbc_xDoubleMove.insets = new Insets(0, 0, 5, 0);
			gbc_xDoubleMove.gridx = 2;
			gbc_xDoubleMove.gridy = 1;
			mrXPanel.add(xDoubleMove, gbc_xDoubleMove);
			
			xTable = new JTable();
			xTable.setBorder(new LineBorder(new Color(64, 64, 64)));
			GridBagConstraints gbc_xTable = new GridBagConstraints();
			gbc_xTable.gridwidth = 3;
			gbc_xTable.gridheight = 3;
			gbc_xTable.insets = new Insets(0, 0, 5, 0);
			gbc_xTable.fill = GridBagConstraints.BOTH;
			gbc_xTable.gridx = 0;
			gbc_xTable.gridy = 2;
			mrXPanel.add(xTable, gbc_xTable);
			
			JPanel detectivePanel = new JPanel();
			GridBagConstraints gbc_detectivePanel = new GridBagConstraints();
			gbc_detectivePanel.insets = new Insets(0, 0, 5, 0);
			gbc_detectivePanel.anchor = GridBagConstraints.SOUTH;
			gbc_detectivePanel.fill = GridBagConstraints.HORIZONTAL;
			gbc_detectivePanel.gridx = 0;
			gbc_detectivePanel.gridy = 1;
			playerPanel.add(detectivePanel, gbc_detectivePanel);
			detectivePanel.setLayout(new GridLayout(0, 1, 0, 0));
			
			JPanel detective1 = new JPanel();
			detectivePanel.add(detective1);
			GridBagLayout gbl_detective1 = new GridBagLayout();
			gbl_detective1.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective1.rowHeights = new int[] {20, 20, 0};
			gbl_detective1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective1.setLayout(gbl_detective1);
			
			JLabel detectivePhoto1 = new JLabel("");
			detectivePhoto1.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveBlue.jpg")));
			GridBagConstraints gbc_detectivePhoto1 = new GridBagConstraints();
			gbc_detectivePhoto1.gridheight = 2;
			gbc_detectivePhoto1.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto1.gridx = 0;
			gbc_detectivePhoto1.gridy = 0;
			detective1.add(detectivePhoto1, gbc_detectivePhoto1);
			
			JLabel detectiveName1 = new JLabel("Annie");
			GridBagConstraints gbc_detectiveName1 = new GridBagConstraints();
			gbc_detectiveName1.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName1.gridwidth = 3;
			gbc_detectiveName1.gridx = 1;
			gbc_detectiveName1.gridy = 0;
			detective1.add(detectiveName1, gbc_detectiveName1);
			
			JLabel taxi1 = new JLabel("Taxis");
			taxi1.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi1 = new GridBagConstraints();
			gbc_taxi1.insets = new Insets(0, 0, 0, 5);
			gbc_taxi1.gridx = 1;
			gbc_taxi1.gridy = 1;
			detective1.add(taxi1, gbc_taxi1);
			
			JLabel bus1 = new JLabel("Buses");
			bus1.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus1 = new GridBagConstraints();
			gbc_bus1.insets = new Insets(0, 0, 0, 5);
			gbc_bus1.gridx = 2;
			gbc_bus1.gridy = 1;
			detective1.add(bus1, gbc_bus1);
			
			JLabel train1 = new JLabel("Trains");
			train1.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train1 = new GridBagConstraints();
			gbc_train1.gridx = 3;
			gbc_train1.gridy = 1;
			detective1.add(train1, gbc_train1);
			
			JPanel detective2 = new JPanel();
			detectivePanel.add(detective2);
			GridBagLayout gbl_detective2 = new GridBagLayout();
			gbl_detective2.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective2.rowHeights = new int[] {20, 20, 0};
			gbl_detective2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective2.setLayout(gbl_detective2);
			
			JLabel detectivePhoto2 = new JLabel("");
			detectivePhoto2.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveGreen.jpg")));
			GridBagConstraints gbc_detectivePhoto2 = new GridBagConstraints();
			gbc_detectivePhoto2.gridheight = 2;
			gbc_detectivePhoto2.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto2.gridx = 0;
			gbc_detectivePhoto2.gridy = 0;
			detective2.add(detectivePhoto2, gbc_detectivePhoto2);
			
			JLabel detectiveName2 = new JLabel("Frank");
			GridBagConstraints gbc_detectiveName2 = new GridBagConstraints();
			gbc_detectiveName2.gridwidth = 3;
			gbc_detectiveName2.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName2.gridx = 1;
			gbc_detectiveName2.gridy = 0;
			detective2.add(detectiveName2, gbc_detectiveName2);
			
			JLabel taxi2 = new JLabel("Taxis");
			taxi2.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi2 = new GridBagConstraints();
			gbc_taxi2.insets = new Insets(0, 0, 0, 5);
			gbc_taxi2.gridx = 1;
			gbc_taxi2.gridy = 1;
			detective2.add(taxi2, gbc_taxi2);
			
			JLabel bus2 = new JLabel("Buses");
			bus2.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus2 = new GridBagConstraints();
			gbc_bus2.insets = new Insets(0, 0, 0, 5);
			gbc_bus2.gridx = 2;
			gbc_bus2.gridy = 1;
			detective2.add(bus2, gbc_bus2);
			
			JLabel train2 = new JLabel("Trains");
			train2.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train2 = new GridBagConstraints();
			gbc_train2.gridx = 3;
			gbc_train2.gridy = 1;
			detective2.add(train2, gbc_train2);
			
			JPanel detective3 = new JPanel();
			detectivePanel.add(detective3);
			GridBagLayout gbl_detective3 = new GridBagLayout();
			gbl_detective3.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective3.rowHeights = new int[] {20, 20, 0};
			gbl_detective3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective3.setLayout(gbl_detective3);
			
			JLabel detectivePhoto3 = new JLabel("");
			detectivePhoto3.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveYellow.jpg")));
			GridBagConstraints gbc_detectivePhoto3 = new GridBagConstraints();
			gbc_detectivePhoto3.gridheight = 2;
			gbc_detectivePhoto3.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto3.gridx = 0;
			gbc_detectivePhoto3.gridy = 0;
			detective3.add(detectivePhoto3, gbc_detectivePhoto3);
			
			JLabel detectiveName3 = new JLabel("Wang");
			GridBagConstraints gbc_detectiveName3 = new GridBagConstraints();
			gbc_detectiveName3.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName3.gridwidth = 3;
			gbc_detectiveName3.gridx = 1;
			gbc_detectiveName3.gridy = 0;
			detective3.add(detectiveName3, gbc_detectiveName3);
			
			JLabel taxi3 = new JLabel("Taxis");
			taxi3.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi3 = new GridBagConstraints();
			gbc_taxi3.insets = new Insets(0, 0, 0, 5);
			gbc_taxi3.gridx = 1;
			gbc_taxi3.gridy = 1;
			detective3.add(taxi3, gbc_taxi3);
			
			JLabel bus3 = new JLabel("Buses");
			bus3.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus3 = new GridBagConstraints();
			gbc_bus3.insets = new Insets(0, 0, 0, 5);
			gbc_bus3.gridx = 2;
			gbc_bus3.gridy = 1;
			detective3.add(bus3, gbc_bus3);
			
			JLabel train3 = new JLabel("Trains");
			train3.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train3 = new GridBagConstraints();
			gbc_train3.gridx = 3;
			gbc_train3.gridy = 1;
			detective3.add(train3, gbc_train3);
			
			JPanel detective4 = new JPanel();
			detectivePanel.add(detective4);
			GridBagLayout gbl_detective4 = new GridBagLayout();
			gbl_detective4.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective4.rowHeights = new int[] {20, 20, 0};
			gbl_detective4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective4.setLayout(gbl_detective4);
			
			JLabel detectivePhoto4 = new JLabel("");
			detectivePhoto4.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveRed.jpg")));
			GridBagConstraints gbc_detectivePhoto4 = new GridBagConstraints();
			gbc_detectivePhoto4.gridheight = 2;
			gbc_detectivePhoto4.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto4.gridx = 0;
			gbc_detectivePhoto4.gridy = 0;
			detective4.add(detectivePhoto4, gbc_detectivePhoto4);
			
			JLabel detectiveName4 = new JLabel("Weewee");
			GridBagConstraints gbc_detectiveName4 = new GridBagConstraints();
			gbc_detectiveName4.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName4.gridwidth = 3;
			gbc_detectiveName4.gridx = 1;
			gbc_detectiveName4.gridy = 0;
			detective4.add(detectiveName4, gbc_detectiveName4);
			
			JLabel taxi4 = new JLabel("Taxis");
			taxi4.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi4 = new GridBagConstraints();
			gbc_taxi4.insets = new Insets(0, 0, 0, 5);
			gbc_taxi4.gridx = 1;
			gbc_taxi4.gridy = 1;
			detective4.add(taxi4, gbc_taxi4);
			
			JLabel bus4 = new JLabel("Buses");
			bus4.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus4 = new GridBagConstraints();
			gbc_bus4.insets = new Insets(0, 0, 0, 5);
			gbc_bus4.gridx = 2;
			gbc_bus4.gridy = 1;
			detective4.add(bus4, gbc_bus4);
			
			JLabel train4 = new JLabel("Trains");
			train4.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train4 = new GridBagConstraints();
			gbc_train4.gridx = 3;
			gbc_train4.gridy = 1;
			detective4.add(train4, gbc_train4);
			
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			w.getContentPane().add(panel, gbc_panel);
			
			JButton newGame = new JButton("New Game");
			newGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			panel.add(newGame);
			
			JButton saveGame = new JButton("Save Game");
			panel.add(saveGame);
			
			JButton loadGame = new JButton("Load Game");
			panel.add(loadGame);
			w.setVisible(true);

            w.toFront();
            w.setExtendedState(w.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		}
		// public abstract void run();


}



