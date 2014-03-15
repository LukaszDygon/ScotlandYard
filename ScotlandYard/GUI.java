import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.awt.event.InputEvent.*;

public class GUI extends GameVisualiser {
	Point point = new Point(0, 0);
	
		private int getPosX(int node)
		{
			return playerVisualisable.getLocationX(node);
		}
		
		private int getPosY(int node)
		{
			return playerVisualisable.getLocationY(node);
		}
		
		/**
		 * @wbp.parser.entryPoint
		 */
		public void run()
		{
			final JFrame w = new JFrame();
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //w.setDefaultLookAndFeelDecorated(true);
			//all the components
			
			w.pack();
			w.setLocationByPlatform(true);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] {1025, 200, 0};
			gridBagLayout.rowHeights = new int[] {24, 30};
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 1.0};
			w.getContentPane().setLayout(gridBagLayout);
			
			JLayeredPane mapPanel = new JLayeredPane();
			GridBagConstraints gbc_mapPanel = new GridBagConstraints();
			gbc_mapPanel.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc_mapPanel.insets = new Insets(0, 0, 5, 5);
			gbc_mapPanel.gridx = 0;
			gbc_mapPanel.gridy = 0;
			w.getContentPane().add(mapPanel, gbc_mapPanel);
			GridBagLayout gbl_mapPanel = new GridBagLayout();
			gbl_mapPanel.columnWidths = new int[] {1025, 0};
			gbl_mapPanel.rowHeights = new int[] {810, 0};
			gbl_mapPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_mapPanel.rowWeights = new double[]{0.0, 1.0};
			mapPanel.setLayout(gbl_mapPanel);
			
			JLabel mapLabel = new JLabel("");
			mapLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int x = e.getX();
					int y = e.getY();
					point.setLocation(x, y);
					System.out.println(point);
				}
			});
			mapLabel.setIcon(new ImageIcon(GUI.class.getResource(getMap())));
			mapLabel.setVerticalAlignment(SwingConstants.TOP);
			mapLabel.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_mapLabel = new GridBagConstraints();
			gbc_mapLabel.fill = GridBagConstraints.BOTH;
			gbc_mapLabel.insets = new Insets(20, 20, 5, 5);
			gbc_mapLabel.gridx = 0;
			gbc_mapLabel.gridy = 0;
			mapPanel.add(mapLabel, gbc_mapLabel);
			
			final JLayeredPane counters = new JLayeredPane();
			mapPanel.setLayer(counters, 1);
			GridBagConstraints gbc_counters = new GridBagConstraints();
			gbc_counters.fill = GridBagConstraints.BOTH;
			gbc_counters.gridx = 0;
			gbc_counters.gridy = 0;
			mapPanel.add(counters, gbc_counters);

			final JLabel counterDetective1 = new JLabel("d1");
			counterDetective1.setBounds((Integer)getPosX(playerVisualisable.getNodeId(0)), (Integer)getPosY(playerVisualisable.getNodeId(0)), 40,40);
			counters.add(counterDetective1);
			mapPanel.setLayer(counterDetective1, 1);
			counterDetective1.setIcon(new ImageIcon(GUI.class.getResource("/Images/d1.png")));
				
			final JLabel counterDetective2 = new JLabel("d2");
			counterDetective2.setBounds((Integer)getPosX(playerVisualisable.getNodeId(1)), (Integer)getPosY(playerVisualisable.getNodeId(1)), 40,40);
			counters.add(counterDetective2);
			counterDetective2.setIcon(new ImageIcon(GUI.class.getResource("/Images/d2.png")));
			mapPanel.setLayer(counterDetective2, 1);
				
			final JLabel counterDetective3 = new JLabel("d3");
			counterDetective3.setBounds((Integer)getPosX(playerVisualisable.getNodeId(2)), (Integer)getPosY(playerVisualisable.getNodeId(2)), 40,40);
			counters.add(counterDetective3);
			counterDetective3.setIcon(new ImageIcon(GUI.class.getResource("/Images/d3.png")));
			mapPanel.setLayer(counterDetective3, 1);
				
			final JLabel counterDetective4 = new JLabel("d4");
			counterDetective4.setBounds((Integer)getPosX(playerVisualisable.getNodeId(3)), (Integer)getPosY(playerVisualisable.getNodeId(3)), 40,40);
			counters.add(counterDetective4);
			counterDetective4.setIcon(new ImageIcon(GUI.class.getResource("/Images/d4.png")));
			mapPanel.setLayer(counterDetective4, 1);
				
			final JLabel counterX = new JLabel("mrX");
			counterX.setBounds((Integer)getPosX(playerVisualisable.getNodeId(4)), (Integer)getPosY(playerVisualisable.getNodeId(4)), 20,20);
			counters.add(counterX);
			counterX.setIcon(new ImageIcon(GUI.class.getResource("/Images/mrX.png")));
			mapPanel.setLayer(counterX, 1);
			mapPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{mapLabel, counterDetective1, counterDetective2, counterDetective3, counterDetective4, counterX}));

			JPanel playerPanel = new JPanel();
			GridBagConstraints gbc_playerPanel = new GridBagConstraints();
			gbc_playerPanel.insets = new Insets(0, 0, 5, 0);
			gbc_playerPanel.anchor = GridBagConstraints.FIRST_LINE_START;
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
			gbc_mrXPanel.anchor = GridBagConstraints.PAGE_START;
			gbc_mrXPanel.insets = new Insets(0, 0, 5, 0);
			gbc_mrXPanel.fill = GridBagConstraints.HORIZONTAL;
			gbc_mrXPanel.gridx = 0;
			gbc_mrXPanel.gridy = 0;
			
			playerPanel.add(mrXPanel, gbc_mrXPanel);
			GridBagLayout gbl_mrXPanel = new GridBagLayout();
			gbl_mrXPanel.columnWidths = new int[] {60, 60, 60, 60};
			gbl_mrXPanel.rowHeights = new int[] {15, 15, 15, 15, 15, 15, 0};
			gbl_mrXPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE, 0.0};
			gbl_mrXPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
			mrXPanel.setLayout(gbl_mrXPanel);
			
			JLabel xPhoto = new JLabel("");
			xPhoto.setIcon(new ImageIcon(GUI.class.getResource("./Images/mrX.jpg")));
			GridBagConstraints gbc_xPhoto = new GridBagConstraints();
			gbc_xPhoto.gridwidth = 2;
			gbc_xPhoto.gridheight = 2;
			gbc_xPhoto.insets = new Insets(0, 0, 5, 5);
			gbc_xPhoto.gridx = 0;
			gbc_xPhoto.gridy = 0;
			mrXPanel.add(xPhoto, gbc_xPhoto);
			
			JLabel xName = new JLabel("Mr Y");
			GridBagConstraints gbc_xName = new GridBagConstraints();
			gbc_xName.gridwidth = 2;
			gbc_xName.insets = new Insets(0, 0, 5, 0);
			gbc_xName.gridx = 2;
			gbc_xName.gridy = 0;
			mrXPanel.add(xName, gbc_xName);
			
			final JLabel xHiddenMove = new JLabel("Hidden");
			xHiddenMove.setOpaque(true);
			xHiddenMove.setBackground(Color.GRAY);
			GridBagConstraints gbc_xHiddenMove = new GridBagConstraints();
			gbc_xHiddenMove.fill = GridBagConstraints.BOTH;
			gbc_xHiddenMove.insets = new Insets(0, 0, 5, 5);
			gbc_xHiddenMove.gridx = 2;
			gbc_xHiddenMove.gridy = 1;
			mrXPanel.add(xHiddenMove, gbc_xHiddenMove);
			
			final JLabel xDoubleMove = new JLabel("Double");
			xDoubleMove.setOpaque(true);
			xDoubleMove.setToolTipText("");
			xDoubleMove.setBackground(new Color(70, 130, 180));
			GridBagConstraints gbc_xDoubleMove = new GridBagConstraints();
			gbc_xDoubleMove.fill = GridBagConstraints.BOTH;
			gbc_xDoubleMove.insets = new Insets(0, 0, 5, 0);
			gbc_xDoubleMove.gridx = 3;
			gbc_xDoubleMove.gridy = 1;
			mrXPanel.add(xDoubleMove, gbc_xDoubleMove);
			
			final JPanel xMoves = new JPanel();
			GridBagConstraints gbc_xMoves = new GridBagConstraints();
			gbc_xMoves.gridwidth = 4;
			gbc_xMoves.gridheight = 6;
			gbc_xMoves.insets = new Insets(0, 0, 0, 5);
			gbc_xMoves.fill = GridBagConstraints.BOTH;
			gbc_xMoves.gridx = 0;
			gbc_xMoves.gridy = 2;
			mrXPanel.add(xMoves, gbc_xMoves);
			GridBagLayout gbl_xMoves = new GridBagLayout();
			gbl_xMoves.columnWidths = new int[] {30, 30, 30, 30, 0};
			gbl_xMoves.rowHeights = new int[] {15, 15, 15, 15, 15, 15, 0};
			gbl_xMoves.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_xMoves.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			xMoves.setLayout(gbl_xMoves);
			
			setMoves(xMoves);
		
			JPanel detectivePanel = new JPanel();
			GridBagConstraints gbc_detectivePanel = new GridBagConstraints();
			gbc_detectivePanel.insets = new Insets(0, 0, 5, 0);
			gbc_detectivePanel.anchor = GridBagConstraints.PAGE_END;
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
			
			JLabel detectiveName1 = new JLabel("Cheeky Viktor");
			GridBagConstraints gbc_detectiveName1 = new GridBagConstraints();
			gbc_detectiveName1.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName1.gridwidth = 3;
			gbc_detectiveName1.gridx = 1;
			gbc_detectiveName1.gridy = 0;
			detective1.add(detectiveName1, gbc_detectiveName1);
			
			JLabel taxi1 = new JLabel("Taxis");
			taxi1.setOpaque(true);
			taxi1.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi1 = new GridBagConstraints();
			gbc_taxi1.fill = GridBagConstraints.BOTH;
			gbc_taxi1.insets = new Insets(0, 0, 0, 5);
			gbc_taxi1.gridx = 1;
			gbc_taxi1.gridy = 1;
			detective1.add(taxi1, gbc_taxi1);
			
			JLabel bus1 = new JLabel("Buses");
			bus1.setOpaque(true);
			bus1.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus1 = new GridBagConstraints();
			gbc_bus1.fill = GridBagConstraints.BOTH;
			gbc_bus1.insets = new Insets(0, 0, 0, 5);
			gbc_bus1.gridx = 2;
			gbc_bus1.gridy = 1;
			detective1.add(bus1, gbc_bus1);
			
			JLabel train1 = new JLabel("Trains");
			train1.setOpaque(true);
			train1.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train1 = new GridBagConstraints();
			gbc_train1.fill = GridBagConstraints.BOTH;
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
			
			JLabel detectiveName2 = new JLabel("Wrong Wladimir");
			GridBagConstraints gbc_detectiveName2 = new GridBagConstraints();
			gbc_detectiveName2.gridwidth = 3;
			gbc_detectiveName2.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName2.gridx = 1;
			gbc_detectiveName2.gridy = 0;
			detective2.add(detectiveName2, gbc_detectiveName2);
			
			JLabel taxi2 = new JLabel("Taxis");
			taxi2.setOpaque(true);
			taxi2.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi2 = new GridBagConstraints();
			gbc_taxi2.fill = GridBagConstraints.BOTH;
			gbc_taxi2.insets = new Insets(0, 0, 0, 5);
			gbc_taxi2.gridx = 1;
			gbc_taxi2.gridy = 1;
			detective2.add(taxi2, gbc_taxi2);
			
			JLabel bus2 = new JLabel("Buses");
			bus2.setOpaque(true);
			bus2.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus2 = new GridBagConstraints();
			gbc_bus2.fill = GridBagConstraints.BOTH;
			gbc_bus2.insets = new Insets(0, 0, 0, 5);
			gbc_bus2.gridx = 2;
			gbc_bus2.gridy = 1;
			detective2.add(bus2, gbc_bus2);
			
			JLabel train2 = new JLabel("Trains");
			train2.setOpaque(true);
			train2.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train2 = new GridBagConstraints();
			gbc_train2.fill = GridBagConstraints.BOTH;
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
			
			JLabel detectiveName3 = new JLabel("Serious Yulia");
			GridBagConstraints gbc_detectiveName3 = new GridBagConstraints();
			gbc_detectiveName3.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName3.gridwidth = 3;
			gbc_detectiveName3.gridx = 1;
			gbc_detectiveName3.gridy = 0;
			detective3.add(detectiveName3, gbc_detectiveName3);
			
			JLabel taxi3 = new JLabel("Taxis");
			taxi3.setOpaque(true);
			taxi3.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi3 = new GridBagConstraints();
			gbc_taxi3.fill = GridBagConstraints.BOTH;
			gbc_taxi3.insets = new Insets(0, 0, 0, 5);
			gbc_taxi3.gridx = 1;
			gbc_taxi3.gridy = 1;
			detective3.add(taxi3, gbc_taxi3);
			
			JLabel bus3 = new JLabel("Buses");
			bus3.setOpaque(true);
			bus3.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus3 = new GridBagConstraints();
			gbc_bus3.fill = GridBagConstraints.BOTH;
			gbc_bus3.insets = new Insets(0, 0, 0, 5);
			gbc_bus3.gridx = 2;
			gbc_bus3.gridy = 1;
			detective3.add(bus3, gbc_bus3);
			
			JLabel train3 = new JLabel("Trains");
			train3.setOpaque(true);
			train3.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train3 = new GridBagConstraints();
			gbc_train3.fill = GridBagConstraints.BOTH;
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
			
			JLabel detectiveName4 = new JLabel("Wise Mykola");
			GridBagConstraints gbc_detectiveName4 = new GridBagConstraints();
			gbc_detectiveName4.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName4.gridwidth = 3;
			gbc_detectiveName4.gridx = 1;
			gbc_detectiveName4.gridy = 0;
			detective4.add(detectiveName4, gbc_detectiveName4);
			
			JLabel taxi4 = new JLabel("Taxis");
			taxi4.setOpaque(true);
			taxi4.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi4 = new GridBagConstraints();
			gbc_taxi4.fill = GridBagConstraints.BOTH;
			gbc_taxi4.insets = new Insets(0, 0, 0, 5);
			gbc_taxi4.gridx = 1;
			gbc_taxi4.gridy = 1;
			detective4.add(taxi4, gbc_taxi4);
			
			JLabel bus4 = new JLabel("Buses");
			bus4.setOpaque(true);
			bus4.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus4 = new GridBagConstraints();
			gbc_bus4.fill = GridBagConstraints.BOTH;
			gbc_bus4.insets = new Insets(0, 0, 0, 5);
			gbc_bus4.gridx = 2;
			gbc_bus4.gridy = 1;
			detective4.add(bus4, gbc_bus4);
			
			JLabel train4 = new JLabel("Trains");
			train4.setOpaque(true);
			train4.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train4 = new GridBagConstraints();
			gbc_train4.fill = GridBagConstraints.BOTH;
			gbc_train4.gridx = 3;
			gbc_train4.gridy = 1;
			detective4.add(train4, gbc_train4);
			
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			w.getContentPane().add(panel, gbc_panel);
			
			JButton newGame = new JButton("New Game");
			newGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initialisable.initialiseGame(4);
					//debugging
					counterDetective1.setBounds((Integer)getPosX(playerVisualisable.getNodeId(0)), (Integer)getPosY(playerVisualisable.getNodeId(0)), 40,40);
					counterDetective2.setBounds((Integer)getPosX(playerVisualisable.getNodeId(1)), (Integer)getPosY(playerVisualisable.getNodeId(1)), 40,40);
					counterDetective3.setBounds((Integer)getPosX(playerVisualisable.getNodeId(2)), (Integer)getPosY(playerVisualisable.getNodeId(2)), 40,40);
					counterDetective4.setBounds((Integer)getPosX(playerVisualisable.getNodeId(3)), (Integer)getPosY(playerVisualisable.getNodeId(3)), 40,40);
					counterX.setBounds((Integer)getPosX(playerVisualisable.getNodeId(4)), (Integer)getPosY(playerVisualisable.getNodeId(4)), 40,40);
				}
			});
			panel.add(newGame);
			
			final JButton saveGame = new JButton("Save Game");
			saveGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					JFileChooser c = new JFileChooser("./SaveFiles");
					int returnVal = c.showSaveDialog(saveGame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						System.out.println("SAVE");
						File filename = c.getSelectedFile();
						System.out.println(""+filename.getName());
						
						try {
							FileOutputStream saveFile = new FileOutputStream (filename);
						    ByteArrayOutputStream baos = serializableSY.save();
						    
						    baos.writeTo(saveFile);
						    saveFile.close();
						    
						    
						} catch(IOException ioe) {
						    // Handle exception here
						    ioe.printStackTrace();
						}
					}
				}
			});
			panel.add(saveGame);
			
			final JButton loadGame = new JButton("Load Game");
			loadGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					JFileChooser c = new JFileChooser("./SaveFiles");
					int returnVal = c.showOpenDialog(loadGame);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						System.out.println("LOAD");
						File filename = c.getSelectedFile();
                        try
                        {
                            FileInputStream loadFile = new FileInputStream(filename);
                            byte []buffer = new byte[loadFile.available()];
                            loadFile.read(buffer,0,loadFile.available());
						    serializableSY.load(new ByteArrayInputStream(buffer));
                        } catch (IOException ioe)
                        {
                            System.out.print("LOAD FAIL");
                        }
						
						counterDetective1.setBounds((Integer)getPosX(playerVisualisable.getNodeId(0)), (Integer)getPosY(playerVisualisable.getNodeId(0)), 40,40);
						counterDetective2.setBounds((Integer)getPosX(playerVisualisable.getNodeId(1)), (Integer)getPosY(playerVisualisable.getNodeId(1)), 40,40);
						counterDetective3.setBounds((Integer)getPosX(playerVisualisable.getNodeId(2)), (Integer)getPosY(playerVisualisable.getNodeId(2)), 40,40);
						counterDetective4.setBounds((Integer)getPosX(playerVisualisable.getNodeId(3)), (Integer)getPosY(playerVisualisable.getNodeId(3)), 40,40);
						counterX.setBounds((Integer)getPosX(playerVisualisable.getNodeId(4)), (Integer)getPosY(playerVisualisable.getNodeId(4)), 40,40);
					}
				}
			});
			panel.add(loadGame);
			
			w.setVisible(true);

            w.toFront();
            w.setExtendedState(w.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            setMoves(xMoves);
		}
		
		/**
		 * @wbp.parser.entryPoint
		 */
		private String getMap() 
		{
			return mapVisualisable.getMapFilename();
		}
		// sets moves of Mr X table
		private void setMoves(JPanel xMoves) {
			xMoves.removeAll();
			xMoves.validate();
			for (int n = 0; n<24; n++)
			{
				JLabel move = new JLabel((n+1) + " + ticket");
				GridBagConstraints gbc_move = new GridBagConstraints();
				gbc_move.insets = new Insets(0, 0, 5, 5);
				gbc_move.gridx = Math.round((n)/6);
				gbc_move.gridy = n%6;
				xMoves.add(move, gbc_move);
			}
		}
		
		
}



