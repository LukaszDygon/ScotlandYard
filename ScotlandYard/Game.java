import java.nio.*;
import java.io.*;

public class Game {
    private Map map;
    private PlayerManager playerManager;

    public static void main(String[] args) {
    	Game game = new Game();
    	game.run();
    }


    public void run()
    {
        map = new Map("./graph.txt","./pos.txt","./Images/map.jpg");
        playerManager = new PlayerManager();
        GameState gameState = new GameState(this);
        playerManager.init(4,gameState); // 4 detectives

        GUI gui = new GUI();
        gui.registerVisualisable(gameState);
        gui.registerInitialisable(gameState);
        gui.registerMapVisualisable(map);
        gui.registerControllable(gameState);

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

    public static void fail(String failMessage)
    {
        System.err.println(failMessage);
        System.exit(1);
    }

    public static void fastprintln(String string)
    {
        System.out.println(string);
    }
}