import java.awt.*;

import javax.swing.*;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class GUI extends GameVisualiser {
	Point point = new Point(0, 0);
	private JPanel detectivePanel_1;
	
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
			w.getContentPane().setBackground(Color.DARK_GRAY);
			w.setResizable(false);
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //w.setDefaultLookAndFeelDecorated(true);
			//all the components
			
			w.pack();
			w.setLocationByPlatform(true);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] {1025, 200, 0, 0};
			gridBagLayout.rowHeights = new int[] {24, 30};
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 1.0};
			w.getContentPane().setLayout(gridBagLayout);
			
			////////////MAP///////////
			
			final JLayeredPane mapPanel = new JLayeredPane();
			mapPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			mapPanel.setBackground(new Color(64, 64, 64));
			GridBagConstraints gbc_mapPanel = new GridBagConstraints();
			gbc_mapPanel.fill = GridBagConstraints.BOTH;
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
			
			setMap(mapPanel);
			
			////////////COUNTERS////////////
			
			final JLayeredPane counters = new JLayeredPane();
			mapPanel.setLayer(counters, 1);
			GridBagConstraints gbc_counters = new GridBagConstraints();
			gbc_counters.fill = GridBagConstraints.BOTH;
			gbc_counters.gridx = 0;
			gbc_counters.gridy = 0;
			mapPanel.add(counters, gbc_counters);

			setCounters(mapPanel, counters);
			
			//////////PLAYERS/////////////
			
			final JPanel playerPanel = new JPanel();
			playerPanel.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_playerPanel = new GridBagConstraints();
			gbc_playerPanel.fill = GridBagConstraints.BOTH;
			gbc_playerPanel.insets = new Insets(25, 0, 5, 0);
			gbc_playerPanel.anchor = GridBagConstraints.WEST;
			gbc_playerPanel.gridx = 1;
			gbc_playerPanel.gridy = 0;
			w.getContentPane().add(playerPanel, gbc_playerPanel);
			GridBagLayout gbl_playerPanel = new GridBagLayout();
			gbl_playerPanel.columnWidths = new int[]{0, 0};
			gbl_playerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_playerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_playerPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			playerPanel.setLayout(gbl_playerPanel);
			
			///////// MR X////////
			
			final JPanel mrXPanel = new JPanel();
			mrXPanel.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_mrXPanel = new GridBagConstraints();
			gbc_mrXPanel.anchor = GridBagConstraints.LINE_START;
			gbc_mrXPanel.insets = new Insets(5, 0, 0, 0);
			gbc_mrXPanel.fill = GridBagConstraints.VERTICAL;
			gbc_mrXPanel.gridx = 0;
			gbc_mrXPanel.gridy = 0;
			playerPanel.add(mrXPanel, gbc_mrXPanel);
			GridBagLayout gbl_mrXPanel = new GridBagLayout();
			gbl_mrXPanel.columnWidths = new int[] {40, 40, 40, 40, 0};
			mrXPanel.setLayout(gbl_mrXPanel);
			
			setMrX(mrXPanel);

			/////// DETECTIVES///////
					
			detectivePanel_1 = new JPanel();
			detectivePanel_1.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_detectivePanel_1 = new GridBagConstraints();
			gbc_detectivePanel_1.insets = new Insets(0, 0, 5, 0);
			gbc_detectivePanel_1.anchor = GridBagConstraints.PAGE_END;
			gbc_detectivePanel_1.fill = GridBagConstraints.VERTICAL;
			gbc_detectivePanel_1.gridx = 0;
			gbc_detectivePanel_1.gridy = 1;
			playerPanel.add(detectivePanel_1, gbc_detectivePanel_1);
			detectivePanel_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			setDetectives(detectivePanel_1);
			
			/////////// BUTTONS /////////////
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			w.getContentPane().add(panel, gbc_panel);
			
			JButton newGame = new JButton("New Game");
			newGame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			newGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					initialisable.initialiseGame(4);
					//debugging
					setCounters(mapPanel, counters);
					setMrX(mrXPanel);
					setDetectives(detectivePanel_1);
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

                        controllable.saveGame(filename.getAbsolutePath());
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
					    controllable.loadGame(filename.getAbsolutePath());
						
					    setCounters(mapPanel, counters);
						setMrX(mrXPanel);
						setDetectives(detectivePanel_1);
					}
				}
			});
			panel.add(loadGame);
			
			w.setVisible(true);

            w.toFront();
            w.setSize(1260, 890);
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
			List<Initialisable.TicketType> moveList = visualisable.getMoveList(0);
			for (int n = 0; n<24; n++)
			{
				JLabel move = new JLabel((n+1) + ": Train" /*+ moveList.get(n)*/);
				GridBagConstraints gbc_move = new GridBagConstraints();
				move.setForeground(Color.WHITE);
				gbc_move.insets = new Insets(0, 0, 5, 5);
				gbc_move.gridx = Math.round((n)/6);
				gbc_move.gridy = n%6;
				xMoves.add(move, gbc_move);
			}
			xMoves.repaint();
		}
		
		// paints counters
		private void setCounters(JLayeredPane mapPanel, JLayeredPane counters)
		{
			counters.removeAll();
			final JLabel counterDetective1 = new JLabel("d1");
			counterDetective1.setBounds((Integer)getPosX(playerVisualisable.getNodeId(0))-20, (Integer)getPosY(playerVisualisable.getNodeId(0))-20, 40,40);
			counters.add(counterDetective1);
			mapPanel.setLayer(counterDetective1, 1);
			counterDetective1.setIcon(new ImageIcon(GUI.class.getResource("/Images/d1.png")));
				
			final JLabel counterDetective2 = new JLabel("d2");
			counterDetective2.setBounds((Integer)getPosX(playerVisualisable.getNodeId(1))-20, (Integer)getPosY(playerVisualisable.getNodeId(1))-20, 40,40);
			counters.add(counterDetective2);
			counterDetective2.setIcon(new ImageIcon(GUI.class.getResource("/Images/d2.png")));
			mapPanel.setLayer(counterDetective2, 1);
				
			final JLabel counterDetective3 = new JLabel("d3");
			counterDetective3.setBounds((Integer)getPosX(playerVisualisable.getNodeId(2))-20, (Integer)getPosY(playerVisualisable.getNodeId(2))-20, 40,40);
			counters.add(counterDetective3);
			counterDetective3.setIcon(new ImageIcon(GUI.class.getResource("/Images/d3.png")));
			mapPanel.setLayer(counterDetective3, 1);
				
			final JLabel counterDetective4 = new JLabel("d4");
			counterDetective4.setBounds((Integer)(getPosX(playerVisualisable.getNodeId(3))-20), (Integer)getPosY(playerVisualisable.getNodeId(3))-20, 40,40);
			counters.add(counterDetective4);
			counterDetective4.setIcon(new ImageIcon(GUI.class.getResource("/Images/d4.png")));
			mapPanel.setLayer(counterDetective4, 1);
				
			final JLabel counterX = new JLabel("mrX");
			counterX.setBounds((Integer)getPosX(playerVisualisable.getNodeId(4))-20, (Integer)getPosY(playerVisualisable.getNodeId(4))-20, 40,40);
			counters.add(counterX);
			counterX.setIcon(new ImageIcon(GUI.class.getResource("/Images/mrX.png")));
			mapPanel.setLayer(counterX, 1);
			mapPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{counterDetective1, counterDetective2, counterDetective3, counterDetective4, counterX}));
			counters.revalidate();
			counters.repaint();
		}
		
		private void setMap(JLayeredPane mapPanel)
		{
			JLabel mapLabel = new JLabel("");
			mapLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					
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
			gbc_mapLabel.insets = new Insets(0, 0, 5, 5);
			gbc_mapLabel.gridx = 0;
			gbc_mapLabel.gridy = 0;
			mapPanel.add(mapLabel, gbc_mapLabel);
		}
		private void setMrX(JPanel mrXPanel)
		{
			mrXPanel.removeAll();
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
			xName.setForeground(Color.WHITE);
			GridBagConstraints gbc_xName = new GridBagConstraints();
			gbc_xName.gridwidth = 2;
			gbc_xName.insets = new Insets(0, 0, 5, 0);
			gbc_xName.gridx = 2;
			gbc_xName.gridy = 0;
			mrXPanel.add(xName, gbc_xName);
			
			final JLabel xHiddenMove = new JLabel("Hidden");
			xHiddenMove.setFont(new Font("Tahoma", Font.PLAIN, 22));
			xHiddenMove.setHorizontalAlignment(SwingConstants.CENTER);
			xHiddenMove.setText(""+ visualisable.getNumberOfTickets(Initialisable.TicketType.SecretMove, 4));
			xHiddenMove.setOpaque(true);
			xHiddenMove.setBackground(Color.GRAY);
			GridBagConstraints gbc_xHiddenMove = new GridBagConstraints();
			gbc_xHiddenMove.weightx = 1.0;
			gbc_xHiddenMove.insets = new Insets(0, 0, 5, 0);
			gbc_xHiddenMove.fill = GridBagConstraints.BOTH;
			gbc_xHiddenMove.gridx = 2;
			gbc_xHiddenMove.gridy = 1;
			mrXPanel.add(xHiddenMove, gbc_xHiddenMove);
			
			final JLabel xDoubleMove = new JLabel("Double");
			xDoubleMove.setFont(new Font("Tahoma", Font.PLAIN, 22));
			xDoubleMove.setHorizontalAlignment(SwingConstants.CENTER);
			xDoubleMove.setText(""+ visualisable.getNumberOfTickets(Initialisable.TicketType.DoubleMove, 4));
			xDoubleMove.setOpaque(true);
			xDoubleMove.setToolTipText("");
			xDoubleMove.setBackground(new Color(70, 130, 180));
			GridBagConstraints gbc_xDoubleMove = new GridBagConstraints();
			gbc_xDoubleMove.weightx = 1.0;
			gbc_xDoubleMove.fill = GridBagConstraints.BOTH;
			gbc_xDoubleMove.insets = new Insets(0, 0, 5, 0);
			gbc_xDoubleMove.gridx = 3;
			gbc_xDoubleMove.gridy = 1;
			mrXPanel.add(xDoubleMove, gbc_xDoubleMove);
			
			final JPanel xMoves = new JPanel();
			xMoves.setForeground(Color.WHITE);
			xMoves.setBackground(Color.DARK_GRAY);
			GridBagConstraints gbc_xMoves = new GridBagConstraints();
			gbc_xMoves.gridwidth = 5;
			gbc_xMoves.gridheight = 6;
			gbc_xMoves.insets = new Insets(0, 0, 0, 5);
			gbc_xMoves.fill = GridBagConstraints.BOTH;
			gbc_xMoves.gridx = 0;
			gbc_xMoves.gridy = 2;
			mrXPanel.add(xMoves, gbc_xMoves);
			GridBagLayout gbl_xMoves = new GridBagLayout();
			gbl_xMoves.columnWidths = new int[] {60, 60, 60, 60, 0};
			gbl_xMoves.rowHeights = new int[] {15, 15, 15, 15, 15, 15, 0};
			gbl_xMoves.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_xMoves.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			xMoves.setLayout(gbl_xMoves);
			
			setMoves(xMoves);
			mrXPanel.revalidate();
		}
		
		private void setDetectives(JPanel detectivePanel)
		{
			detectivePanel.removeAll();
			JPanel detective1 = new JPanel();
			detective1.setOpaque(false);
			detectivePanel.add(detective1);
			GridBagLayout gbl_detective1 = new GridBagLayout();
			gbl_detective1.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective1.rowHeights = new int[] {20, 20, 0};
			gbl_detective1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective1.setLayout(gbl_detective1);
			
			setDetective1(detective1);
			
			JPanel detective2 = new JPanel();
			detective2.setBackground(Color.DARK_GRAY);
			detectivePanel.add(detective2);
			GridBagLayout gbl_detective2 = new GridBagLayout();
			gbl_detective2.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective2.rowHeights = new int[] {20, 20, 0};
			gbl_detective2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective2.setLayout(gbl_detective2);
			
			setDetective2(detective2);
			
			JPanel detective3 = new JPanel();
			detective3.setBackground(Color.DARK_GRAY);
			detective3.setForeground(Color.LIGHT_GRAY);
			detectivePanel.add(detective3);
			GridBagLayout gbl_detective3 = new GridBagLayout();
			gbl_detective3.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective3.rowHeights = new int[] {20, 20, 0};
			gbl_detective3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective3.setLayout(gbl_detective3);
			
			setDetective3(detective3);

			JPanel detective4 = new JPanel();
			detective4.setBackground(Color.DARK_GRAY);
			detectivePanel.add(detective4);
			GridBagLayout gbl_detective4 = new GridBagLayout();
			gbl_detective4.columnWidths = new int[] {60, 40, 40, 40};
			gbl_detective4.rowHeights = new int[] {20, 20, 0};
			gbl_detective4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			gbl_detective4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			detective4.setLayout(gbl_detective4);
			
			setDetective4(detective4);
		}
		private void setDetective1(JPanel detective1){
			
			detective1.removeAll();
			JLabel detectivePhoto1 = new JLabel("");
			detectivePhoto1.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveBlue.jpg")));
			GridBagConstraints gbc_detectivePhoto1 = new GridBagConstraints();
			gbc_detectivePhoto1.gridheight = 2;
			gbc_detectivePhoto1.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto1.gridx = 0;
			gbc_detectivePhoto1.gridy = 0;
			detective1.add(detectivePhoto1, gbc_detectivePhoto1);
			
			JLabel detectiveName1 = new JLabel("Cheeky Viktor");
			detectiveName1.setForeground(Color.WHITE);
			GridBagConstraints gbc_detectiveName1 = new GridBagConstraints();
			gbc_detectiveName1.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName1.gridwidth = 3;
			gbc_detectiveName1.gridx = 1;
			gbc_detectiveName1.gridy = 0;
			detective1.add(detectiveName1, gbc_detectiveName1);
			
			setTaxi(detective1, 0);
			setBus(detective1, 0);
			setTrain(detective1, 0);
			detective1.repaint();
		}
		private void setDetective2(JPanel detective2)
		{
			detective2.removeAll();
			JLabel detectivePhoto2 = new JLabel("");
			detectivePhoto2.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveGreen.jpg")));
			GridBagConstraints gbc_detectivePhoto2 = new GridBagConstraints();
			gbc_detectivePhoto2.gridheight = 2;
			gbc_detectivePhoto2.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto2.gridx = 0;
			gbc_detectivePhoto2.gridy = 0;
			detective2.add(detectivePhoto2, gbc_detectivePhoto2);
			
			JLabel detectiveName2 = new JLabel("Angry Wladimir");
			detectiveName2.setForeground(Color.WHITE);
			GridBagConstraints gbc_detectiveName2 = new GridBagConstraints();
			gbc_detectiveName2.gridwidth = 3;
			gbc_detectiveName2.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName2.gridx = 1;
			gbc_detectiveName2.gridy = 0;
			detective2.add(detectiveName2, gbc_detectiveName2);
			
			setTaxi(detective2, 1);
			setBus(detective2, 1);
			setTrain(detective2, 1);
			detective2.repaint();
		}
		private void setDetective3(JPanel detective3)
		{
			detective3.removeAll();
			JLabel detectivePhoto3 = new JLabel("");
			detectivePhoto3.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveYellow.jpg")));
			GridBagConstraints gbc_detectivePhoto3 = new GridBagConstraints();
			gbc_detectivePhoto3.gridheight = 2;
			gbc_detectivePhoto3.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto3.gridx = 0;
			gbc_detectivePhoto3.gridy = 0;
			detective3.add(detectivePhoto3, gbc_detectivePhoto3);
			
			JLabel detectiveName3 = new JLabel("Serious Yulia");
			detectiveName3.setForeground(Color.WHITE);
			GridBagConstraints gbc_detectiveName3 = new GridBagConstraints();
			gbc_detectiveName3.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName3.gridwidth = 3;
			gbc_detectiveName3.gridx = 1;
			gbc_detectiveName3.gridy = 0;
			detective3.add(detectiveName3, gbc_detectiveName3);
			
			setTaxi(detective3, 2);
			setBus(detective3, 2);
			setTrain(detective3, 2);
			detective3.repaint();
		}
		private void setDetective4(JPanel detective4)
		{
			detective4.removeAll();
			JLabel detectivePhoto4 = new JLabel("");
			detectivePhoto4.setIcon(new ImageIcon(GUI.class.getResource("./Images/detectiveRed.jpg")));
			GridBagConstraints gbc_detectivePhoto4 = new GridBagConstraints();
			gbc_detectivePhoto4.gridheight = 2;
			gbc_detectivePhoto4.insets = new Insets(0, 0, 0, 5);
			gbc_detectivePhoto4.gridx = 0;
			gbc_detectivePhoto4.gridy = 0;
			detective4.add(detectivePhoto4, gbc_detectivePhoto4);
			
			JLabel detectiveName4 = new JLabel("Wise Mykola");
			detectiveName4.setForeground(Color.WHITE);
			GridBagConstraints gbc_detectiveName4 = new GridBagConstraints();
			gbc_detectiveName4.insets = new Insets(0, 0, 5, 0);
			gbc_detectiveName4.gridwidth = 3;
			gbc_detectiveName4.gridx = 1;
			gbc_detectiveName4.gridy = 0;
			detective4.add(detectiveName4, gbc_detectiveName4);
			
			setTaxi(detective4, 3);
			setBus(detective4, 3);
			setTrain(detective4, 3);
			detective4.repaint();
		}
		
		private void setTaxi(JPanel detective, Integer id)
		{
			JLabel taxi = new JLabel("Taxis");
			taxi.setFont(new Font("Tahoma", Font.PLAIN, 22));
			taxi.setHorizontalAlignment(SwingConstants.CENTER);
			taxi.setText(""+ visualisable.getNumberOfTickets(Initialisable.TicketType.Taxi, id));
			taxi.setOpaque(true);
			taxi.setBackground(new Color(255, 215, 0));
			GridBagConstraints gbc_taxi = new GridBagConstraints();
			gbc_taxi.fill = GridBagConstraints.BOTH;
			gbc_taxi.gridx = 1;
			gbc_taxi.gridy = 1;
			detective.add(taxi, gbc_taxi);
		}
		private void setBus(JPanel detective, Integer id)
		
		{
			JLabel bus = new JLabel("Buses");
			bus.setFont(new Font("Tahoma", Font.PLAIN, 22));
			bus.setHorizontalAlignment(SwingConstants.CENTER);
			bus.setText(""+ visualisable.getNumberOfTickets(Initialisable.TicketType.Bus, id));
			bus.setOpaque(true);
			bus.setBackground(new Color(34, 139, 34));
			GridBagConstraints gbc_bus = new GridBagConstraints();
			gbc_bus.fill = GridBagConstraints.BOTH;
			gbc_bus.gridx = 2;
			gbc_bus.gridy = 1;
			detective.add(bus, gbc_bus);
		}
		
		private void setTrain(JPanel detective, Integer id)
		{
			JLabel train = new JLabel("Trains\n\n");
			train.setFont(new Font("Tahoma", Font.PLAIN, 22));
			train.setHorizontalAlignment(SwingConstants.CENTER);
			train.setText(""+ visualisable.getNumberOfTickets(Initialisable.TicketType.Underground, id));
			train.setOpaque(true);
			train.setBackground(new Color(255, 69, 0));
			GridBagConstraints gbc_train = new GridBagConstraints();
			gbc_train.fill = GridBagConstraints.BOTH;
			gbc_train.gridx = 3;
			gbc_train.gridy = 1;
			detective.add(train, gbc_train);
		}
		
		
}



