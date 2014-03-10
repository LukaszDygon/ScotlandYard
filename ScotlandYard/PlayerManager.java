import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Created by devsh on 10/03/14.
 */

public class PlayerManager implements PlayerVisualisable, SerializableSY
{
    private Game game;
    private Map map;
    private DetectiveManager detectiveManager;
    private MrXManager mrXManager;

    public PlayerManager(Game game_in)
    {
        game = game_in;
    }

    public void init(Integer numberOfDetectives)
    {
        map = game.getMap();

        detectiveManager = new DetectiveManager(numberOfDetectives);
        mrXManager = new MrXManager(1,numberOfDetectives);
    }

    public void newGameInit()
    {
        detectiveManager.newGameInit();
        mrXManager.newGameInit();
    }

    /**
     * Function to get the x position of a node given its id
     * @param nodeId The node id you wish to find the location of
     * @return An integer value of a nodes x position
     */
    public Integer getLocationX(Integer nodeId)
    {
        return map.getNodePos(nodeId).data[0];
    }

    /**
     * Function to get the y position of a node given its id
     * @param nodeId The node id you wish to find the location of
     * @return An integer value of a nodes y position
     */
    public Integer getLocationY(Integer nodeId)
    {
        return map.getNodePos(nodeId).data[1];
    }

    /**
     * Function to return a list of detective IDS from the game. These IDs will
     * be unique identifiers given to the player
     * @return The list of detective IDs
     */
    public List<Integer> getDetectiveIdList()
    {
        return detectiveManager.getIdList();
    }

    /**
     * Function to get the list of Mr X IDs. In this version of the game you will really only
     * have one Mr X so this function will return a list with only one entry. However this
     * may change down the line...
     * @return The list of Mr X IDs
     */
    public List<Integer> getMrXIdList()
    {
        return mrXManager.getIdList();
    }

    /**
     * Function to retireve the current location of a player. The location is an id of a graph node
     * @param playerId The id of the player
     * @return The node id that the player is on
     */
    public Integer getNodeId(Integer playerId)
    {
        if (playerId<detectiveManager.getDetectiveCount())
        {
            return detectiveManager.getNodeId(playerId);
        }
        else
            return mrXManager.getNodeId(playerId);
    }

    public void setPlayerPosition(Integer playerId,Integer nodeId)
    {
        //some validation here


        if (playerId<detectiveManager.getDetectiveCount())
        {
            detectiveManager.setPlayerPosition(playerId,nodeId);
        }
        else
            mrXManager.setPlayerPosition(playerId,nodeId);
    }
    public ByteArrayOutputStream save()
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ByteArrayOutputStream subBuffers[] = {mrXManager.save(), detectiveManager.save()};

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
        mrXManager.load(new ByteArrayInputStream(bytesData));


        buffer.read(bytesInt,0,4);
        size = ByteBuffer.wrap(bytesInt).getInt();
        buffer.read(bytesData,0,size);
        detectiveManager.load(new ByteArrayInputStream(bytesData));
    }
}
