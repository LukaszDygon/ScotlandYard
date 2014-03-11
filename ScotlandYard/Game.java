import java.nio.*;
import java.io.*;

public class Game implements SerializableSY {
    private GUI gui;
    private GameState gameState;
    private Map map;
    private PlayerManager playerManager;


	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
	
	
	public void run()
	{
<<<<<<< HEAD
		GameState state = new GameState();
		GUI gui = new GUI();
		gui.registerMapVisualisable(state);
		gui.run();
=======
        map = new Map("./graph.txt","./pos.txt","./Images/map.jpg");
		gui = new GUI();
        playerManager = new PlayerManager(this);
        GameState gameState = new GameState(this);

        playerManager.init(4); // 4 detectives
		gui.registerMapVisualisable(map);
>>>>>>> 8737924271ba0d52556207003a4520040eeaeafc
		
		//initialise then start your GUI
        gui.run();
	}

    public PlayerManager getPlayerManager()
    {
        return playerManager;
    }

    public Map getMap()
    {
        return map;
    }

    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ByteArrayOutputStream subBuffers[] = {gameState.save(), map.save(), playerManager.save()};

        for (ByteArrayOutputStream subBuffer : subBuffers)
        {
            Integer size = subBuffer.size();
            buffer.write(ByteBuffer.allocateDirect(4).putInt(size).array(),0,4);
            buffer.write(subBuffer.toByteArray(),0,size);
        }

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        byte bytesInt[] = new byte[4];
        buffer.read(bytesInt,0,4);
        Integer size = ByteBuffer.wrap(bytesInt).getInt();

        byte bytesData[] = new byte[8192];
        buffer.read(bytesData,0,size);
        gameState.load(new ByteArrayInputStream(bytesData));


        buffer.read(bytesInt,0,4);
        size = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesData,0,size);
        map.load(new ByteArrayInputStream(bytesData));


        buffer.read(bytesInt,0,4);
        size = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesData,0,size);
        playerManager.load(new ByteArrayInputStream(bytesData));
    }

    public static void fail(String failMessage)
    {
        System.err.println(failMessage);
        System.exit(1);
    }

}
