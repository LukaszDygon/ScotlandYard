/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements MapVisualisable {
	
	/**
	 * Vairable that will hold the filename for the map
	 */
	private String mapFilename;
	public int taxiDetective1 = 0;
	public int busDetective1 = 0;
	public int trainDetective1 = 0;
	public int taxiDetective2 = 0;
	public int busDetective2 = 0;
	public int trainDetective2 = 0;
	public int taxiDetective3 = 0;
	public int busDetective3 = 0;
	public int trainDetective3 = 0;
	public int taxiDetective4 = 0;
	public int busDetective4 = 0;
	public int trainDetective4 = 0;
	public int xDoubleMove = 0;
	public int xHiddenMove = 0;
	public int turnNumber = 0;
	public int posMrX;
	public int posDetective1;
	public int posDetective2;
	public int posDetective3;
	public int posDetective4;
	
	
	public void newGameInitialise(int players)
	{
		taxiDetective1 = 11;
		busDetective1 = 8;
		trainDetective1 = 4;
		taxiDetective2 = 11;
		busDetective2 = 8;
		trainDetective2 = 4;
		taxiDetective3 = 11;
		busDetective3 = 8;
		trainDetective3 = 4;
		taxiDetective4 = 11;
		busDetective4 = 8;
		trainDetective4 = 4;
		xDoubleMove = 2;
		xHiddenMove = 4;
		turnNumber = 24;
		generateStartPosition();
		
	}
	
	private void generateStartPosition()
	{
		int position = (int)Math.random() * 198 + 1;
		posDetective1 = position;
		while  (position == posDetective1 ||
				position == posDetective2 ||
				position == posDetective3 ||
				position == posDetective4)
		{
			position = (int)Math.random() * 198 + 1;
		}
		posDetective2 = position;
		while  (position == posDetective1)
		{
			position = (int)Math.random() * 198 + 1;
		}
		posDetective3 = position;
		while  (position == posDetective1 ||
				position == posDetective2)
		{
			position = (int)Math.random() * 198 + 1;
		}
		posDetective4 = position;
		while  (position == posDetective1 ||
				position == posDetective2 ||
				position == posDetective3)
		{
			position = (int)Math.random() * 198 + 1;
		}
		posMrX = position;
	}
	
	
	/**
	 * Concrete implementation of the MapVisualisable getMapFilename function
	 * @return The map filename
	 */
	public String getMapFilename()
	{
		return mapFilename;
	}

}
