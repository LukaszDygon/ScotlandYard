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
        map = new Map("./graph.txt","./pos.txt","./Images/map.jpg");
        gui = new GUI();
        playerManager = new PlayerManager(this);
        gameState = new GameState(this);

        playerManager.init(4); // 4 detectives
        gui.registerMapVisualisable(map);
        gui.registerPlayerVisualisable(playerManager);
        gui.registerInitialisable(gameState);
        gui.registerSerialisableSY(this);

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
            buffer.write(ByteBuffer.allocate(4).putInt(size).array(),0,4);
        }

        for (ByteArrayOutputStream subBuffer : subBuffers)
        {
            Integer size = subBuffer.size();
            buffer.write(subBuffer.toByteArray(),0,size);
        }

        return buffer;

    }
    // need to have some safeguards against IOExceptions when user feeds us bad files
    public void load(ByteArrayInputStream buffer)
    {
        if (buffer.available()<12)
            return;

        byte bytesInt[] = new byte[4];
        Integer []size = new Integer[3];

        buffer.read(bytesInt,0,4);
        size[0] = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesInt,0,4);
        size[1] = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesInt,0,4);
        size[2] = ByteBuffer.wrap(bytesInt).getInt();


        if (buffer.available()<(size[0]+size[1]+size[2]))
            return;

        byte bytesData[] = new byte[size[0]+size[1]+size[2]];

        buffer.read(bytesData,0,size[0]);
        gameState.load(new ByteArrayInputStream(bytesData,0,size[0]));
        buffer.read(bytesData,0,size[1]);
        map.load(new ByteArrayInputStream(bytesData,0,size[1]));
        buffer.read(bytesData,0,size[2]);
        playerManager.load(new ByteArrayInputStream(bytesData,0,size[1]));
    }

    public static void fail(String failMessage)
    {
        System.err.println(failMessage);
        System.exit(1);
    }

}